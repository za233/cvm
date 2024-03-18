#include <iostream>
#include<ctime>
#include<cstdlib>
#include<cstdio>
#include"cvm-vm.h"
#include"twofish.h"
Runtime runtime;
class MyModule : public BuiltinModule
{
public:
    unsigned char key[16];
    
    MyModule()
    {
        this->name = "flag";
        this->add_func("ooo", (VM_CALL_FUNC) &MyModule::set_key);
        this->add_func("ggg", (VM_CALL_FUNC) &MyModule::get_result);
        memset(this->key, 0, sizeof(this->key));
    }
    void set_key(VirtualMachine *vm)
    {
        RuntimeStack *runtime_stack = vm->get_runtime_stack();
        uint64_t buffer = *((uint64_t *) runtime_stack->top(0));
        uint8_t *mem = vm->get_memory(buffer);
        for(int i = 0; i < 16; i++)
            this->key[i] = mem[i];
        vm->get_op_stack()->push(0);
    }
    void get_result(VirtualMachine *vm)
    {
        RuntimeStack *runtime_stack = vm->get_runtime_stack();
        vm->get_op_stack()->push(0);
        uint64_t size = *((uint64_t *) runtime_stack->top(0));
        if(size % 16 != 0)
            return;
        uint64_t addr = *((uint64_t *) runtime_stack->top(8));
        uint8_t *out_buf = vm->get_memory(addr);
        addr = *((uint64_t *) runtime_stack->top(16));
        uint8_t *in_buf = vm->get_memory(addr);
        twofish_t *f = Twofish_setup(this->key, 16);
        for(int i = 0; i < size; i += 16)
            Twofish_encryt(f, &(in_buf[i]), &(out_buf[i]));
        
        /*uint8_t test[256]; 
        for(int i = 0; i < size; i += 16)
            Twofish_decryt(f, &(out_buf[i]), &(test[i]));

        for(int i = 0; i < size; i += 16)
        {
            for(int j = 0; j < 16; j++)
            {
                printf("%02X ", test[i + j]);
                if(j % 16 == 15) 
                {
                    printf("\n");
                }
            }
        }*/
        free(f);
        
    }
	void on_file_load(VirtualMachine *vm) {}
	void on_vm_start(VirtualMachine *vm) {}
	void on_vm_exit(VirtualMachine *vm) {}
    BuiltinModule *clone()
    {
        MyModule *mod = new MyModule();
        memcpy(mod->key, this->key, 16);
        return mod;
    }
};

int main(int argc, char** argv) 
{
	srand(time(0));
    
    runtime.add_module(new MyModule());
    if(fopen("cvm.bin", "rb") == NULL)
    {
        printf("file not exist!\n");
        return -1;
    }
        
	BytecodeFile *f = new BytecodeFile("cvm.bin");
	f->load();
	if(runtime.new_process(f))
        runtime.start();
    else
        printf("fail to initialized!\n");
	delete f;
	return 0;
}
