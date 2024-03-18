
char num_char[17] = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
unsigned long ctrl, vmdata;
unsigned char hello[22] = {119, 101, 108, 99, 111, 109, 101, 33, 10, 0};
unsigned char fail_init[23] = {102, 97, 105, 108, 101, 100, 32, 116, 111, 32, 105, 110, 105, 116, 105, 97, 108, 105, 122, 101, 33, 10, 0};
unsigned char give_flag[27] = {112, 108, 101, 97, 115, 101, 32, 103, 105, 118, 101, 32, 109, 101, 32, 121, 111, 117, 114, 32, 102, 108, 97, 103, 58, 10, 0};
void print_hex_char(unsigned char c)
{
	os.putchar(num_char[(c >> 4) & 0xf]);
	os.putchar(num_char[c & 0xf]);
}
void print_hex(unsigned long value, unsigned char size)
{
	if(size > 8)
	{
		return;
	}
	int i;
	for(i = size - 1; i >= 0; i -= 1)
	{
		print_hex_char((value >> (i * 8)) & 0xff);
	}
	return;
}
void print_oct(unsigned long value)
{
	unsigned long ptr = value, idx = 0;
	char buf[36];
	while(ptr != 0)
	{
		buf[idx] = ptr % 10;
		idx += 1;
		ptr /= 10;
	}
	int i;
	for(i = idx - 1; i >= 0; i -= 1)
	{
		os.putchar(num_char[buf[i]]);
	}
}


void wait_for(unsigned long shm, unsigned int state)
{
    unsigned int get = 0;
    while(get != state)
    {
        os.wait();
        os.shmrd(vm.ref(get), 0, 4, shm);
    }
}
void notify_for(unsigned long shm,unsigned int state)
{   
    os.shmwt(vm.ref(state), 0, 4, shm);
    os.notify(0);
}
unsigned int FATHER_FINISH = 0x44512abc,SON_FINISH = -1, HANDLER_CALL = 0xdeadbeef, HANDLER_KILL = 0xaa114514, HANDLER_FINISH = 0x23332457;
unsigned char key[16] = {67, 89, 172, 60, 23, 152, 65, 89, 214, 43, 112, 133, 200, 14, 77, 223};
unsigned char ok[32] = {0x7a, 0x2c, 0xb7, 0x06, 0x48, 0x90, 0x68, 0x55, 0xc4, 0xfc, 0x12, 0x6e, 0x5c, 0xfe, 0x2b, 0x96, 0x04, 0x10, 0x3c, 0x8d, 0xbc, 0xd5, 0xec, 0x77, 0xa6, 0xea, 0xcf, 0x40, 0xf2, 0xfa, 0xd7, 0x14};
unsigned char wrong[6] = {119, 114, 111, 110, 103, 0};
unsigned char right[6] = {114, 105, 103, 104, 116, 0};
void do_father_logic(unsigned long son_pid)
{
    int i;
    unsigned char buf[32], enc[32], tmp[32];
    for(i = 0; i < 32; i += 1)
    {
        buf[i] = os.getchar();
        tmp[i] = buf[i];
    }
    for(i = 0; i < 4; i += 1)
    {
        set_mem(2 * i, buf[8 * i] | (buf[8 * i + 1] << 8) | (buf[8 * i + 2] << 16) | (buf[8 * i + 3] << 24));
        set_mem(2 * i + 1, buf[8 * i + 4] | (buf[8 * i + 5] << 8) | (buf[8 * i + 6] << 16) | (buf[8 * i + 7] << 24));
    }
    notify_for(ctrl, FATHER_FINISH);
    flag.ooo(key);
    wait_for(ctrl, SON_FINISH);
    for(i = 0; i < 4; i += 1)
    {
        int v = get_mem(2 * i);
        buf[8 * i] = v & 0xff;
        buf[8 * i + 1] = (v >> 8) & 0xff;
        buf[8 * i + 2] = (v >> 16) & 0xff;
        buf[8 * i + 3] = (v >> 24) & 0xff;
        v = get_mem(2 * i + 1);
        buf[8 * i + 4] = v & 0xff;
        buf[8 * i + 5] = (v >> 8) & 0xff;
        buf[8 * i + 6] = (v >> 16) & 0xff;
        buf[8 * i + 7] = (v >> 24) & 0xff;

    }
    
    flag.ggg(buf, enc, 32);
    for(i = 0; i < 32; i += 1)
    {
        if(enc[i] != ok[i])
        {
            os.puts(wrong);
            return;
        }
        
    }


    os.puts(right);
    os.putchar(10);
    os.putchar(68);
    os.putchar(117);
    os.putchar(98);
    os.putchar(104);
    os.putchar(101);
    os.putchar(67);
    os.putchar(84);
    os.putchar(70);
    os.putchar(123);

    for(i = 0; i < 32; i += 1)
    {
        os.putchar(tmp[i]);
    }
    os.putchar(125);
    os.putchar(10);

}
int get_pc()
{
    int pc;
    os.shmrd(vm.ref(pc), 0, 4, vmdata);
    return pc;
}
void set_pc(int pc)
{
    os.shmwt(vm.ref(pc), 0, 4, vmdata);
}
int get_reg(int id)
{
    int value;
    os.shmrd(vm.ref(value), id * 4 + 4, 4, vmdata);
    return value;
}
void set_reg(int id, int value)
{
    os.shmwt(vm.ref(value), id * 4 + 4, 4, vmdata);
}
int get_mem(int addr)
{
    int value;
    os.shmrd(vm.ref(value), (addr + 20) * 4, 4, vmdata);
    return value;
}
void set_mem(int addr, int value)
{
    os.shmwt(vm.ref(value), (addr + 20) * 4, 4, vmdata);
}
unsigned char opcode[334] = {1, 0, 0, 0, 0, 4, 4, 1, 2, 0, 0, 0, 7, 0, 4, 3, 0, 4, 1, 1, 1, 0, 0, 0, 4, 6, 1, 2, 0, 0, 0, 7, 0, 4, 5, 0, 6, 3, 0, 4, 2, 1, 0, 0, 0, 0, 4, 3, 1, 0, 0, 0, 0, 4, 5, 1, 90, 71, 110, 35, 5, 3, 0, 4, 3, 1, 188, 171, 137, 120, 4, 7, 1, 32, 0, 0, 0, 4, 6, 16, 2, 8, 0, 6, 5, 0, 7, 4, 7, 16, 3, 5, 0, 2, 11, 0, 7, 4, 7, 1, 190, 186, 254, 202, 4, 8, 1, 16, 0, 0, 0, 4, 6, 16, 2, 7, 0, 6, 5, 8, 0, 4, 8, 11, 7, 8, 4, 7, 16, 1, 5, 0, 7, 4, 1, 16, 1, 4, 6, 1, 204, 204, 204, 204, 5, 0, 6, 12, 0, 4, 1, 1, 86, 69, 35, 18, 4, 7, 1, 32, 0, 0, 0, 4, 6, 16, 1, 8, 0, 6, 5, 0, 7, 4, 7, 16, 3, 5, 0, 1, 11, 0, 7, 4, 7, 1, 239, 190, 173, 222, 4, 8, 1, 16, 0, 0, 0, 4, 6, 16, 1, 7, 0, 6, 5, 8, 0, 4, 8, 11, 7, 8, 4, 7, 16, 2, 5, 0, 7, 4, 2, 16, 2, 4, 6, 1, 221, 221, 221, 221, 5, 0, 6, 12, 0, 4, 2, 1, 1, 0, 0, 0, 5, 5, 0, 4, 5, 1, 16, 0, 0, 0, 13, 5, 0, 4, 7, 1, 1, 0, 0, 0, 4, 6, 11, 7, 6, 15, 55, 0, 0, 0, 1, 2, 0, 0, 0, 7, 0, 4, 4, 6, 16, 1, 2, 6, 1, 1, 0, 0, 0, 4, 6, 1, 2, 0, 0, 0, 7, 0, 4, 5, 0, 6, 4, 6, 16, 2, 2, 6, 1, 1, 0, 0, 0, 5, 4, 0, 4, 4, 1, 4, 0, 0, 0, 14, 0, 4, 15, 7, 0, 0, 0, 0}; 
void do_son_logic(unsigned long fa_pid)
{
    wait_for(ctrl, FATHER_FINISH);
    int i;
    for(i = 1; i < 17; i += 1)
    {
        unsigned long son = os.fork();
        if(son == -1)
        {
			os.puts(fail_init);
			os.exit();
		}
        else
        {
            if(son == 0)
            {
                dispatcher(i);
                return;
            }
        }
    }
    set_pc(0);
    while(get_pc() < 334 && opcode[get_pc()] != 0)
    {
        int pc = get_pc();
        set_pc(pc + 1);
        notify_for(ctrl, opcode[pc]);
        wait_for(ctrl, HANDLER_FINISH);
    }
    notify_for(ctrl, HANDLER_KILL);
    notify_for(ctrl, SON_FINISH);
    
    
}
int read4bytes(unsigned char mem[0], int offset)
{
    int value;
    value = vm.load(vm.ref(mem[0]) + offset, 4);
    return value;
}
void write4bytes(unsigned char mem[0], int offset, int value)
{
    vm.store(vm.ref(mem[0]) + offset, 4, value);
}
void dispatcher(unsigned int wait_op)
{
    unsigned int get = 0;
    while(get != HANDLER_KILL)
    {
        os.wait();
        os.shmrd(vm.ref(get), 0, 4, ctrl);
        if(get == wait_op)
        {
            
            int pc = get_pc();
            if(get == 1)
            {
                set_reg(0, read4bytes(opcode, pc));
                pc += 4;
            }
            else if(get == 2)
            {
                set_mem(get_reg(opcode[pc]), get_reg(0));
                pc += 1;
            }
            else if(get == 3)
            {
                set_reg(0, get_mem(get_reg(opcode[pc])));
                pc += 1;
            }
            else if(get == 4)
            {
                set_reg(opcode[pc], get_reg(0));
                pc += 1;
            }
            else if(get == 5)
            {
                set_reg(0, get_reg(opcode[pc]) + get_reg(opcode[pc + 1]));
                pc += 2;
            }
            else if(get == 6)
            {
                set_reg(0, get_reg(opcode[pc]) - get_reg(opcode[pc + 1]));
                pc += 2;
            }
            else if(get == 7)
            {
                set_reg(0, get_reg(opcode[pc]) * get_reg(opcode[pc + 1]));
                pc += 2;
            }
            else if(get == 8)
            {
                set_reg(0, get_reg(opcode[pc]) / get_reg(opcode[pc + 1]));
                pc += 2;
            }
            else if(get == 9)
            {
                set_reg(0, get_reg(opcode[pc]) & get_reg(opcode[pc + 1]));
                pc += 2;
            }
            else if(get == 10)
            {
                set_reg(0, get_reg(opcode[pc]) | get_reg(opcode[pc + 1]));
                pc += 2;
            }
            else if(get == 11)
            {
                set_reg(0, get_reg(opcode[pc]) ^ get_reg(opcode[pc + 1]));
                pc += 2;
            }
            else if(get == 12)
            {
                set_reg(0, ~ get_reg(opcode[pc]));
                pc += 1;
            }
            else if(get == 13)
            {
               
                if(get_reg(opcode[pc]) == get_reg(opcode[pc + 1]))
                {
                    set_reg(0, 1);
                }
                else
                {
                    set_reg(0, 0);
                }
                pc += 2;
            }
            else if(get == 14)
            {
                
                
                if(get_reg(opcode[pc]) > get_reg(opcode[pc + 1]))
                {
                    set_reg(0, 1);
                }
                else
                {
                    set_reg(0, 0);
                }
                pc += 2;
            }
            else if(get == 15)
            {
                int offset = read4bytes(opcode, pc);
                if(get_reg(0) != 0)
                {
                    pc = offset;
                }
                else
                {
                    pc += 4;
                }
                    


            } 
            else if(get == 16)
            {
                set_reg(0, get_reg(opcode[pc]));
                pc += 1;
            }
            
            set_pc(pc);
            notify_for(ctrl, HANDLER_FINISH);
        }
        
    }
    

}


int main()
{
	os.puts(hello);
	ctrl = os.shmget(4);
    vmdata = os.shmget(1024);
	if(ctrl != 0 && vmdata != 0)
	{
        os.puts(give_flag);
		unsigned long fa_pid = os.getpid();
		unsigned long son_pid = os.fork();
		if(son_pid == -1)
		{
			os.puts(fail_init);
			os.exit();
		}
		else
		{
			if(son_pid == 0)
            {
                do_son_logic(fa_pid);
            }
            else
            {
                do_father_logic(son_pid);
                os.shmfree(vmdata);
                os.shmfree(ctrl);
                
            }
		}
		
	}
	else
	{
		os.puts(fail_init);
		os.exit();
	} 
	return 0;
}
