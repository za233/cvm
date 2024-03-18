#include"cvm-vm.h"
class OsModule : public BuiltinModule
{
private:
	std::vector<FILE*> fd_map;
public:
	OsModule();
	~OsModule();
	void on_file_load(VirtualMachine *vm); 
	void on_vm_start(VirtualMachine *vm);
	void on_vm_end(VirtualMachine *vm);
	void on_vm_exit(VirtualMachine *vm);
	void os_puts(VirtualMachine *vm);
	void os_putchar(VirtualMachine *vm);
	void os_getchar(VirtualMachine *vm);
	void os_fopen(VirtualMachine *vm);
	void os_fseek(VirtualMachine *vm);
	void os_fread(VirtualMachine *vm);
	void os_fwrite(VirtualMachine *vm);
	void os_fclose(VirtualMachine *vm);
	void os_exit(VirtualMachine *vm);
	void os_fork(VirtualMachine *vm);
	void os_shmget(VirtualMachine *vm);
	void os_shmwt(VirtualMachine *vm);
	void os_shmrd(VirtualMachine *vm);
	void os_shmfree(VirtualMachine *vm);
	void os_start(VirtualMachine *vm);
	void os_getpid(VirtualMachine *vm);
	void os_notify(VirtualMachine *vm);
	void os_wait(VirtualMachine *vm);
	BuiltinModule *clone(); 
};
