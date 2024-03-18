#include"cvm-module.h"
OsModule::OsModule()
{
	DBG_LOG("[!] OsModule allocated");
	this->name = "os"; 
	// void puts(ptr *str);
	this->add_func("puts", (VM_CALL_FUNC) &OsModule::os_puts);
	// void putchar(val char);
	this->add_func("putchar", (VM_CALL_FUNC) &OsModule::os_putchar);
	// val getchar();
	this->add_func("getchar", (VM_CALL_FUNC) &OsModule::os_getchar);
	// val fopen(ptr *filename, val attr);
	this->add_func("fopen", (VM_CALL_FUNC) &OsModule::os_fopen);
	// void fseek(val fp, val offset, val type); 
	this->add_func("fseek", (VM_CALL_FUNC) &OsModule::os_fseek);
	// void fread(ptr *buf, val ele_size, val ele_count, val fp);
	this->add_func("fread", (VM_CALL_FUNC) &OsModule::os_fread);
	// void fwrite(ptr *buf, val ele_size, val ele_count, val fp);
	this->add_func("fwrite", (VM_CALL_FUNC) &OsModule::os_fwrite);
	// void fclose(val fp); 
	this->add_func("fclose", (VM_CALL_FUNC) &OsModule::os_fclose);
	// void exit(); 
	this->add_func("exit", (VM_CALL_FUNC) &OsModule::os_exit);
	// val fork(); 
	this->add_func("fork", (VM_CALL_FUNC) &OsModule::os_fork);
	// val shmget(); 
	this->add_func("shmget", (VM_CALL_FUNC) &OsModule::os_shmget);
	// val shmwt(ptr *buf, val offset, val count, val md);
	this->add_func("shmwt", (VM_CALL_FUNC) &OsModule::os_shmwt);
	// val shmfd(ptr *buf, val offset, val count, val md);
	this->add_func("shmrd", (VM_CALL_FUNC) &OsModule::os_shmrd);
	// void shmget(val md);
	this->add_func("shmfree", (VM_CALL_FUNC) &OsModule::os_shmfree);
	// void start(ptr *filepath);
	this->add_func("start", (VM_CALL_FUNC) &OsModule::os_start);
	// void getpid(ptr *filepath);
	this->add_func("getpid", (VM_CALL_FUNC) &OsModule::os_getpid);
	// void notify(val pid);
	this->add_func("notify", (VM_CALL_FUNC) &OsModule::os_notify);
	// val wait();
	this->add_func("wait", (VM_CALL_FUNC) &OsModule::os_wait);
}
OsModule::~OsModule()
{
	DBG_LOG("[!] OsModule free");
	for(uint64_t i = 0; i < this->fd_map.size(); i++)
	{
		if(fd_map[i] != NULL)
			fclose(fd_map[i]);
	}
}
void OsModule::on_vm_exit(VirtualMachine *vm)
{
	DBG_LOG("[!] OsModule::on_vm_exit");
	for(uint64_t i = 0; i < this->fd_map.size(); i++)
	{
		if(fd_map[i] != NULL)
			fclose(fd_map[i]);
	}
}
void OsModule::on_vm_start(VirtualMachine *vm)
{
	DBG_LOG("[!] OsModule::on_vm_start");
	this->fd_map.clear();
}
void OsModule::on_file_load(VirtualMachine *vm)
{
	DBG_LOG("[!] OsModule::on_file_load");
}
void OsModule::os_puts(VirtualMachine *vm)
{
	 
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	uint64_t addr = *((uint64_t *)runtime_stack->top(0));
	while(!vm->get_status())
	{
		uint8_t c = vm->read_byte(addr++);
		if(c == 0)
			break;
		putchar(c);
		
	}
	OperationStack *op_stack = vm->get_op_stack();
	op_stack->push(0);
}
void OsModule::os_putchar(VirtualMachine *vm)
{
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	uint64_t c = *((uint64_t *)runtime_stack->top(0));
	putchar(c);
	OperationStack *op_stack = vm->get_op_stack();
	op_stack->push(0);
} 
void OsModule::os_getchar(VirtualMachine *vm)
{
	OperationStack *op_stack = vm->get_op_stack();
	op_stack->push(getchar());
}

void OsModule::os_fopen(VirtualMachine *vm)
{
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	OperationStack *op_stack = vm->get_op_stack();
	uint64_t rw = *((uint64_t *)runtime_stack->top(0));
	uint64_t filename = *((uint64_t *)runtime_stack->top(8));
	std::string attribute;
	if((rw & 1) == 1)
		attribute = "w"; 
	else
		attribute = "r";
	if(rw & 2)
		attribute += "+";
	
	FILE *fp = fopen((char*)vm->get_memory(filename), attribute.c_str());
	if(fp == NULL)
	{
		DBG_PRINT("[!] fail to open file: %s\n", vm->get_memory(filename));
		op_stack->push(0);
		return;
	}
	uint64_t fd = 0;
	for(uint64_t i = 0; i < this->fd_map.size(); i++)
	{
		if(this->fd_map[i] == NULL)
		{
			this->fd_map[i] = fp;
			fd = i + 1;
			break;
		}
	}
	if(fd == 0)
	{
		this->fd_map.push_back(fp);
		fd = this->fd_map.size();
	}
	
	op_stack->push(fd);
	
	
}
void OsModule::os_fseek(VirtualMachine *vm)
{
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	OperationStack *op_stack = vm->get_op_stack();
	uint64_t origin = *((uint64_t *)runtime_stack->top(0));
	uint64_t offset = *((uint64_t *)runtime_stack->top(8));
	uint64_t fp_fake = *((uint64_t *)runtime_stack->top(16));
	if(fp_fake == 0 || fp_fake -1 >= this->fd_map.size() || this->fd_map[fp_fake - 1] == NULL)
	{
		op_stack->push(-1);
		return;
	}
	FILE *fp = this->fd_map[fp_fake - 1];
	int ok = fseek(fp, offset, origin);
	op_stack->push(ok);
}
void OsModule::os_fread(VirtualMachine *vm)
{
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	OperationStack *op_stack = vm->get_op_stack();
	uint64_t fp_fake = *((uint64_t *)runtime_stack->top(0));
	uint64_t count = *((uint64_t *)runtime_stack->top(8));
	uint64_t size = *((uint64_t *)runtime_stack->top(16));
	uint64_t buffer = *((uint64_t *)runtime_stack->top(24));
	if(fp_fake == 0 || fp_fake -1 >= this->fd_map.size() || this->fd_map[fp_fake - 1] == NULL)
	{
		op_stack->push(-1);
		return;
	}
	
	FILE *fp = this->fd_map[fp_fake - 1];
	uint64_t ok = fread(vm->get_memory(buffer), size, count, fp);
	op_stack->push(ok);
}
void OsModule::os_fwrite(VirtualMachine *vm)
{
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	OperationStack *op_stack = vm->get_op_stack();
	uint64_t fp_fake = *((uint64_t *)runtime_stack->top(0));
	uint64_t count = *((uint64_t *)runtime_stack->top(8));
	uint64_t size = *((uint64_t *)runtime_stack->top(16));
	uint64_t buffer = *((uint64_t *)runtime_stack->top(24));
	if(fp_fake == 0 || fp_fake -1 >= this->fd_map.size() || this->fd_map[fp_fake - 1] == NULL)
	{
		op_stack->push(-1);
		return;
	}
	FILE *fp = this->fd_map[fp_fake - 1];
	uint64_t ok = fwrite(vm->get_memory(buffer), size, count, fp);
	op_stack->push(ok);
}
void OsModule::os_fclose(VirtualMachine *vm)
{
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	OperationStack *op_stack = vm->get_op_stack();
	uint64_t fd = *((uint64_t *)runtime_stack->top(0));
	if(fd == 0 || fd - 1 >= this->fd_map.size() || this->fd_map[fd - 1] == NULL)
	{
		op_stack->push(0);
		return;
	}
	fclose(this->fd_map[fd - 1]);
	this->fd_map[fd - 1] = NULL;
	op_stack->push(1);
		
}

void OsModule::os_exit(VirtualMachine *vm)
{
	vm->set_pc(-1);
}
void OsModule::os_fork(VirtualMachine *vm)
{
	OperationStack *op_stack = vm->get_op_stack();
	VirtualMachine *ps = vm->fork(); 
	DBG_PRINT("[+] fork from pid(%d) to pid(%d)\n", vm->get_pid(), ps->get_pid()); 
	if(ps == nullptr)
		op_stack->push(-1);
	else
	{
		op_stack->push(ps->get_pid());
		ps->get_op_stack()->push(0);
		vm->get_parent()->add_forked_process(ps);
	}
	
}
void OsModule::os_shmget(VirtualMachine *vm)
{
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	OperationStack *op_stack = vm->get_op_stack();
	uint64_t size = *((uint64_t *)runtime_stack->top(0));
    uint64_t md = vm->get_parent()->new_shared_memory(size);
	op_stack->push(md);
}
void OsModule::os_shmwt(VirtualMachine *vm)
{
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	uint64_t md = *((uint64_t *)runtime_stack->top(0));
	uint64_t count = *((uint64_t *)runtime_stack->top(8));
	uint64_t offset = *((uint64_t *)runtime_stack->top(16));
	uint64_t buffer = *((uint64_t *)runtime_stack->top(24));
	OperationStack *op_stack = vm->get_op_stack();
	bool ok = vm->get_parent()->write_shared_memory(md, count, offset, vm->get_memory(buffer));
	op_stack->push(ok);
}
void OsModule::os_shmrd(VirtualMachine *vm)
{
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	uint64_t md = *((uint64_t *)runtime_stack->top(0));
	uint64_t count = *((uint64_t *)runtime_stack->top(8));
	uint64_t offset = *((uint64_t *)runtime_stack->top(16));
	uint64_t buffer = *((uint64_t *)runtime_stack->top(24));
	OperationStack *op_stack = vm->get_op_stack();
	bool ok = vm->get_parent()->read_shared_memory(md, count, offset, vm->get_memory(buffer));
	op_stack->push(ok);
}
void OsModule::os_shmfree(VirtualMachine *vm)
{
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	uint64_t md = *((uint64_t *)runtime_stack->top(0));
	OperationStack *op_stack = vm->get_op_stack();
	vm->get_parent()->free_shared_memory(md);
	op_stack->push(0);
}
void OsModule::os_start(VirtualMachine *vm)
{
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	uint64_t addr = *((uint64_t *)runtime_stack->top(0));
	BytecodeFile *f = new BytecodeFile((char*)vm->get_memory(addr));
	OperationStack *op_stack = vm->get_op_stack();
	if(f->load())
	{
		VirtualMachine *new_ps = vm->get_parent()->new_process(f);
		if(new_ps != nullptr)
			op_stack->push(new_ps->get_pid());
		else
			op_stack->push(0);
		
	}
	else
		op_stack->push(0);
	delete f;
	
}
void OsModule::os_getpid(VirtualMachine *vm)
{
	OperationStack *op_stack = vm->get_op_stack();
	op_stack->push(vm->get_pid());
}
void OsModule::os_notify(VirtualMachine *vm)
{
	OperationStack *op_stack = vm->get_op_stack();
	RuntimeStack *runtime_stack = vm->get_runtime_stack();
	uint64_t pid = *((uint64_t *)runtime_stack->top(0));
    if(pid != 0)
    {
        VirtualMachine *other = vm->get_parent()->get_ps(pid);
        if(other != nullptr)
        {
            if(vm->get_parent()->set_wake(other))
            {
                op_stack->push(1);
                other->get_op_stack()->push(vm->get_pid());
                return;
            }	
        } 
        op_stack->push(0);
    }
    else
    {
        for(VirtualMachine * ps : vm->get_parent()->get_wait())
        {
            ps->get_op_stack()->push(vm->get_pid());
            vm->get_parent()->get_ready().push_back(ps);
        }
        vm->get_parent()->get_wait().clear();
        op_stack->push(1);
    }
	
}
void OsModule::os_wait(VirtualMachine *vm)
{
	OperationStack *op_stack = vm->get_op_stack();
	vm->get_parent()->set_wait(vm);
}

BuiltinModule *OsModule::clone()
{
	OsModule *new_one = new OsModule();
	for(uint64_t i = 0; i < this->fd_map.size(); i++)
		new_one->fd_map.push_back(this->fd_map[i]);
	return new_one;
}
