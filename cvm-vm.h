#pragma once
#include<vector>
#include<cstdio>
#include<cstdlib>
#include<cstdint>
#include<functional>
#include<string>
#include<map>
#include"cvm-stack.h"
#pragma pack(4)
//#define DBG_MODE
struct GLOBAL_INFO
{
    uint32_t magic_number;
    uint32_t section_count;
    uint32_t max_space;
    uint32_t content_size;
    uint32_t entry_point;
};
struct SECTION_INFO
{
    uint32_t file_offset;
    uint32_t load_base;
    uint32_t attribute;
    uint32_t memory_size;
    uint32_t content_size;
};
struct BYTECODE_HEADER
{
    GLOBAL_INFO info;
    SECTION_INFO sections[0];
};
#pragma pack()

class VirtualMachine;
class BuiltinModule;
struct Descriptor;
typedef void (BuiltinModule::*VM_CALL_FUNC)(VirtualMachine *);
enum VM_ERROR
{
	LOAD_FAIL=1,
	EXIT_NORMALLY,
	FILE_NOT_LOAD,
	STACK_NOT_FOUND,
	INVALID_PC,
	INVALID_ADDRESS,
	STACK_OVER_FLOW,
	STACK_UNDER_FLOW,
	DIVIDE_BY_ZERO, 
	INVALID_OPCODE,
	INVALID_FORMAT,
	INVALID_INSN,
	BUFFER_OVER_FLOW,
	STACK_UNBALANCE,
	NO_SUCH_PROC, 
};
enum OPERAND
{
	END=0,
	IMM_BYTE,
	IMM_SHORT,
	IMM_INT,
	IMM_LONG,
	OFFSET,
	OP_INFO,
};

class Section
{
private:
    uint8_t *buffer;
    int load_base, attribute, memory_size, buffer_size;
    friend class VirtualMachine; 
public:
    Section(int load_base, int attribute, int memory_size, int buffer_size);
    uint8_t *get_buffer();
    ~Section();
};

class BytecodeFile
{
private:
    FILE *fp;
    const char * filepath;
    uint32_t max_space, entry_point;
    std::vector<Section*> sections;
    bool loaded; 
    friend class VirtualMachine; 
public:
    BytecodeFile(const char *filepath);
    bool load();
    bool is_loaded();
    std::vector<Section*> &get_sections();
    ~BytecodeFile();
};

#ifndef DBG_MODE 
#define DBG_LOG(STR)
#define DBG_PRINT(STR, ...)
#else
#define DBG_LOG(STR) puts(STR)
#define DBG_PRINT(STR, ...) printf(STR, __VA_ARGS__)
#endif
 
class BuiltinModule
{

protected:
	std::string name;
	std::map<std::string, VM_CALL_FUNC> func_map;
public:
	void add_func(std::string name, VM_CALL_FUNC ptr);
	std::string get_name();
	VM_CALL_FUNC get_func(std::string &func_name);
	virtual void on_file_load(VirtualMachine *vm) = 0;
	virtual void on_vm_start(VirtualMachine *vm) = 0;
	virtual void on_vm_exit(VirtualMachine *vm) = 0;
	BuiltinModule() {}
	virtual ~BuiltinModule() {};
	virtual BuiltinModule *clone() = 0;
};
class Runtime;
class VirtualMachine : public ErrorHandler
{
private:
	uint32_t pid; 
	bool loaded;
	Runtime *parent;
    uint8_t *memory;
    uint64_t *op_memory;
    uint32_t pc;
    uint32_t max_space;
    OperationStack *op_stack;
    RuntimeStack *runtime_stack;
    std::map<std::string, BuiltinModule*> modules;
    std::vector<std::pair<BuiltinModule&, VM_CALL_FUNC>> call_table;
    BytecodeFile *bytecode;
    int status;
    friend class Runtime;
public:
    VirtualMachine(Runtime *parent, BytecodeFile *bytecode);
    ~VirtualMachine();
    Runtime *get_parent();
    uint8_t *get_memory(uint32_t offset);
    uint32_t get_pid(); 
    
    void stack_overflow();
    void stack_underflow();
    void divide_by_zero();
    void invalid_insn();
    int get_status();
    void read_mem(uint32_t addr, uint32_t size, void *out);
    uint8_t read_byte(uint32_t addr);
    uint16_t read_short(uint32_t addr);
    uint32_t read_int(uint32_t addr);
    uint64_t read_long(uint32_t addr);
    void write_mem(uint32_t addr, uint32_t size, void *in);
    void write_byte(uint32_t addr, uint8_t data);
    void write_short(uint32_t addr, uint16_t data);
    void write_int(uint32_t addr, uint32_t data);
    void write_long(uint32_t addr, uint64_t data);
    void set_pc(uint32_t pc);
    uint32_t get_pc();
    OperationStack *get_op_stack();
    RuntimeStack *get_runtime_stack();
    void add_module(BuiltinModule *module);
    Descriptor *decode(uint64_t *operand_out, uint32_t max_operand_size);
    void load();
    void run();
    VirtualMachine *fork();
    void run_init();
    bool run_step();
	void run_end(); 
    void insn_invalid(uint64_t *operands);
    void insn_load(uint64_t *operands);
    void insn_store(uint64_t *operands);
    void insn_push_imm1(uint64_t *operands);
    void insn_push_imm2(uint64_t *operands);
    void insn_push_imm4(uint64_t *operands);
    void insn_push_imm8(uint64_t *operands);
    void insn_push_bp(uint64_t *operands);
    void insn_pop(uint64_t *operands);
    void insn_add(uint64_t *operands);
    void insn_sub(uint64_t *operands);
    void insn_smul(uint64_t *operands);
    void insn_umul(uint64_t *operands);
    void insn_sdiv(uint64_t *operands);
    void insn_udiv(uint64_t *operands);
    void insn_smod(uint64_t *operands);
    void insn_umod(uint64_t *operands);
    void insn_and(uint64_t *operands);
    void insn_or(uint64_t *operands);
    void insn_xor(uint64_t *operands);
    void insn_shl(uint64_t *operands);
    void insn_lshr(uint64_t *operands);
    void insn_ashr(uint64_t *operands);
    void insn_neg(uint64_t *operands);
    void insn_not(uint64_t *operands);
    void insn_sext(uint64_t *operands);
    void insn_st_arg(uint64_t *operands);
    void insn_pop_arg(uint64_t *operands);
    void insn_call(uint64_t *operands);
    void insn_enter(uint64_t *operands);
    void insn_leave(uint64_t *operands);
    void insn_ret(uint64_t *operands);
    void insn_br(uint64_t *operands);
    void insn_jmp(uint64_t *operands);
    void insn_cmp(uint64_t *operands);
    void insn_alloc(uint64_t *operands);
    void insn_vmcall(uint64_t *operands);

};
class Runtime
{
private:
	
	std::map<std::string, BuiltinModule*> modules;
	std::vector<VirtualMachine*> ready, wait, finish;
	std::map<uint32_t, std::pair<uint8_t*, size_t>> shared_mem;
	
public:
	Runtime();
	void add_module(BuiltinModule *module);
	VirtualMachine *add_uninitialized_process(BytecodeFile *bytecode);
	VirtualMachine *new_process(BytecodeFile *bytecode);
	void add_forked_process(VirtualMachine *process);
	bool set_wait(VirtualMachine *ps);
    bool set_wake(VirtualMachine *ps);
	void start();
	void clear();
    std::vector<VirtualMachine*> &get_ready();
    std::vector<VirtualMachine*> &get_wait();
    std::vector<VirtualMachine*> &get_finish();
	uint32_t new_shared_memory(size_t size);
	void free_shared_memory(uint32_t md);
	bool read_shared_memory(uint32_t md, size_t size, uint64_t offset, uint8_t *buf);
	bool write_shared_memory(uint32_t md, size_t size, uint64_t offset, uint8_t *buf);
	VirtualMachine *get_ps(uint32_t pid);
	~Runtime();
};
struct Descriptor
{
	uint8_t opcode;
	uint8_t operand[3];
	std::function<void(VirtualMachine&,uint64_t *)> handler;
};
