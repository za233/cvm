#include"cvm-vm.h"
#include"cvm-module.h"
#include<algorithm>
void BuiltinModule::add_func(std::string name, VM_CALL_FUNC ptr)
{
	this->func_map[name] = ptr;
}
std::string BuiltinModule::get_name()
{
	return this->name;
}
VM_CALL_FUNC BuiltinModule::get_func(std::string &func_name)
{
	if(this->func_map.count(func_name) > 0)
		return this->func_map[func_name];
	return nullptr;
}
uint8_t *Section::get_buffer()
{
    return this->buffer;
}
Section::Section(int load_base, int attribute, int memory_size, int buffer_size)
{
    this->load_base = load_base;
    this->attribute = attribute;
    this->memory_size = memory_size;
    this->buffer_size = buffer_size;
    this->buffer = (uint8_t *) malloc(buffer_size);
}
Section::~Section()
{
    free(this->buffer);
}
BytecodeFile::BytecodeFile(const char *filepath)
{
    this->filepath = filepath;
    this->fp = fopen(filepath, "rb");
    this->loaded = false; 
}
BytecodeFile::~BytecodeFile()
{
    for(Section * section : this->sections)
        delete section;
    fclose(this->fp);
}
#define DYNAMIC 2
#define STACK 4
#define IMPORT 8
bool BytecodeFile::load()
{
	this->loaded = false;
    if(this->fp == NULL)
        return false;
    BYTECODE_HEADER *header = (BYTECODE_HEADER*) malloc(0x1000);
    size_t read_size = fread(&header->info, sizeof(GLOBAL_INFO), 1, this->fp);
    if(read_size != sizeof(GLOBAL_INFO) && header->info.magic_number != 0xdeadbeef)
    {
        DBG_LOG("[!] invalid magic number");
        free(header);
        
        return false;
    }
    this->max_space = header->info.max_space;
    this->entry_point = header->info.entry_point;
    int section_table_size = sizeof(SECTION_INFO) * header->info.section_count;
    DBG_PRINT("[loader] Magic Number  : %08x\n", header->info.magic_number);
    DBG_PRINT("[loader] Section Count : %d\n", header->info.section_count);
    DBG_PRINT("[loader] Max Space     : %d\n", header->info.max_space);
    DBG_PRINT("[loader] Content Size  : %d\n", header->info.content_size);
    DBG_PRINT("[loader] Entry Point   : %08x\n", header->info.entry_point);

    read_size = fread(&header->sections, 1, section_table_size, this->fp);
    if(read_size != section_table_size)
    {
        DBG_LOG("[!] unexpected end of file while reading section table");
        free(header);
        return false;
    }
    int read_content_size = 0;
    for(int i = 0; i < header->info.section_count; i++)
    {
        SECTION_INFO *info = &header->sections[i];
        DBG_PRINT("[loader] Section : %d\n", i);
        DBG_PRINT("[loader] \t - File Offset  : %d\n", info->file_offset);
        DBG_PRINT("[loader] \t - Load Base    : %08x\n", info->load_base);
        DBG_PRINT("[loader] \t - Attribute    : %08x\n", info->attribute);
        DBG_PRINT("[loader] \t - Memory Size  : %d\n", info->memory_size);
        DBG_PRINT("[loader] \t - Content Size : %d\n", info->content_size);
        Section *section = new Section(info->load_base, info->attribute, info->memory_size, info->content_size);
        this->sections.push_back(section);
        if(!(info->attribute & IMPORT))
        {
        	if(info->load_base + info->memory_size > header->info.max_space 
				|| info->memory_size < info->content_size)
	        {
	        	DBG_LOG("[!] invalid section size");
	        	for(Section * section : this->sections)
	                delete section;
	            this->sections.clear();
	            free(header);
	            return false;
			}
		}
        
        read_size = fread(section->get_buffer(), 1, info->content_size, fp);
        if(read_size != info->content_size)
        {
            DBG_LOG("[!] unexpected end of file while reading section data");
            for(Section * section : this->sections)
                delete section;
            this->sections.clear();
            free(header);
            return false;
        }
        
        read_content_size += read_size;
    }
    if(read_content_size != header->info.content_size)
    {
        DBG_LOG("[!] invalid file size");
        for(Section * section : this->sections)
            delete section;
        this->sections.clear();
        free(header);
        return false;
    }
    free(header);
    this->loaded = true;
    return true;
}
bool BytecodeFile::is_loaded()
{
    return this->loaded;
}
std::vector<Section*> &BytecodeFile::get_sections()
{
    return this->sections;
}
void VirtualMachine::load()
{
	if(!this->bytecode->loaded)
	{
		DBG_LOG("[!] bytecode file not loaded.");
		this->status = LOAD_FAIL;
		return;
	}
	this->op_stack = new OperationStack(this);
	this->runtime_stack = new RuntimeStack(this);
	this->op_memory = (uint64_t *)malloc(1024 * sizeof(uint64_t));
	this->op_stack->set_memory(this->op_memory, 1024);
	 
	this->pc = this->bytecode->entry_point;
	this->memory = (uint8_t *)malloc(this->bytecode->max_space);
	this->max_space = this->bytecode->max_space;
	memset(this->memory, 0, this->bytecode->max_space);
	Section *stack = nullptr, *import_section = nullptr;
	for(Section *section : this->bytecode->get_sections())
	{
		int attribute = section->attribute; 
		
		if(attribute & STACK)
			stack = section;
		else if(attribute & IMPORT)
			import_section = section;
		else
		{
			if(!(attribute & DYNAMIC))
				memcpy(&this->memory[section->load_base], section->buffer, section->buffer_size);
		}
		
	}
	if(stack == nullptr)
	{
		DBG_LOG("[!] stack section not found.");
		this->status = STACK_NOT_FOUND;
		free(this->memory);
		return;
	}
	if(import_section != nullptr)
	{
		
		uint8_t *buffer = import_section->get_buffer(); 
		uint32_t ptr = 0;
		while(ptr < import_section->buffer_size)
		{
			uint32_t size = buffer[ptr++];
			int module_name_len = 0;
			
			for(int i = 0; i < size; i++)
			{
				if(buffer[ptr + i] == ':')
					module_name_len = i;
			}
			std::string module_name((char*)&buffer[ptr], module_name_len);
			std::map<std::string, BuiltinModule*>::iterator iter = this->modules.find(module_name);
			
			if(iter == this->modules.end())
			{
				DBG_PRINT("[!] no such module -> %s\n", module_name.c_str());
				this->status = NO_SUCH_PROC;
				free(this->memory);
				return;
			}
			std::string func_name((char*)&buffer[ptr + module_name_len + 1], size - module_name_len - 1);
			BuiltinModule *module = iter->second;
			VM_CALL_FUNC func = module->get_func(func_name); 
			if(func == nullptr)
			{
				DBG_PRINT("[!] no such function -> %s.", func_name.c_str());
				this->status = NO_SUCH_PROC;
				free(this->memory);
				return;
			}
			this->call_table.push_back(std::pair<BuiltinModule&, VM_CALL_FUNC>(*module, func));
			ptr += size;
		}
	}
	this->runtime_stack->set_memory(this->memory, this->bytecode->max_space);
	this->runtime_stack->set_rsp(stack->load_base + stack->memory_size);
	this->runtime_stack->set_rbp(stack->load_base + stack->memory_size);
	this->status = 0;
	this->loaded = true;
	std::map<std::string, BuiltinModule*>::iterator iter;
	for(iter = this->modules.begin(); iter != this->modules.end(); iter++)
		iter->second->on_file_load(this); 
}

VirtualMachine::VirtualMachine(Runtime *parent, BytecodeFile *bytecode)
{
	this->parent = parent;
	this->bytecode = bytecode;
	this->loaded = false;
	uint32_t r = rand(); 
	while(this->parent->get_ps(r) != nullptr || r == 0)
		r = rand();
	this->pid = r;
	
}
VirtualMachine::~VirtualMachine()
{
	std::map<std::string, BuiltinModule*>::iterator iter;
    DBG_LOG("[!] deallocate object<BuiltinModule>");
	for(iter = this->modules.begin(); iter != this->modules.end(); iter++)
		delete iter->second;
	if(this->loaded)
	{
        DBG_LOG("[!] deallocate runtime stack");
		delete this->runtime_stack;
		delete this->op_stack;
		free(this->op_memory);
	}
	
}
void VirtualMachine::set_pc(uint32_t pc)
{
	this->pc = pc;
}
uint32_t VirtualMachine::get_pc()
{
	return this->pc;
}
OperationStack *VirtualMachine::get_op_stack()
{
	return this->op_stack;
}
RuntimeStack *VirtualMachine::get_runtime_stack()
{
	return this->runtime_stack;
}
uint32_t VirtualMachine::get_pid()
{
	return this->pid;
}
int VirtualMachine::get_status()
{
	return this->status;
}
void VirtualMachine::add_module(BuiltinModule *module)
{
	this->modules[module->get_name()] = module; 
}
uint8_t *VirtualMachine::get_memory(uint32_t offset)
{
	return &this->memory[offset];
}
void VirtualMachine::stack_overflow()
{
	this->status = STACK_OVER_FLOW;
}
void VirtualMachine::stack_underflow()
{
	this->status = STACK_UNDER_FLOW;
}
void VirtualMachine::divide_by_zero()
{
	this->status = DIVIDE_BY_ZERO;
}
void VirtualMachine::invalid_insn()
{
	this->status = INVALID_INSN;
}


Descriptor table[256] = {
	{.opcode = 0, .operand = {END}, .handler = &VirtualMachine::insn_invalid},
	{.opcode = 1, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_load},
	{.opcode = 2, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_store},
	{.opcode = 3, .operand = {IMM_BYTE, END}, .handler = &VirtualMachine::insn_push_imm1},
	{.opcode = 4, .operand = {IMM_SHORT, END}, .handler = &VirtualMachine::insn_push_imm2},
	{.opcode = 5, .operand = {IMM_INT, END}, .handler = &VirtualMachine::insn_push_imm4},
	{.opcode = 6, .operand = {IMM_LONG, END}, .handler = &VirtualMachine::insn_push_imm8},
	{.opcode = 7, .operand = {END}, .handler = &VirtualMachine::insn_invalid},
	{.opcode = 8, .operand = {END}, .handler = &VirtualMachine::insn_push_bp},
	{.opcode = 9, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_pop},
	{.opcode = 10, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_add},
	{.opcode = 11, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_sub},
	{.opcode = 12, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_smul},
	{.opcode = 13, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_umul},
	{.opcode = 14, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_sdiv},
	{.opcode = 15, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_udiv},
	{.opcode = 16, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_smod},
	{.opcode = 17, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_umod},
	{.opcode = 18, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_and},
	{.opcode = 19, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_or},	
	{.opcode = 20, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_xor},
	{.opcode = 21, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_shl},
	{.opcode = 22, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_lshr},
	{.opcode = 23, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_ashr},
	{.opcode = 24, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_neg},
	{.opcode = 25, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_not},
	{.opcode = 26, .operand = {OP_INFO, OP_INFO, END}, .handler = &VirtualMachine::insn_sext},
	{.opcode = 27, .operand = {END}, .handler = &VirtualMachine::insn_st_arg},
	{.opcode = 28, .operand = {OP_INFO, END}, .handler = &VirtualMachine::insn_pop_arg},
	{.opcode = 29, .operand = {OFFSET, END}, .handler = &VirtualMachine::insn_call},
	{.opcode = 30, .operand = {END}, .handler = &VirtualMachine::insn_ret},
	{.opcode = 31, .operand = {OFFSET, END}, .handler = &VirtualMachine::insn_br},
	{.opcode = 32, .operand = {OFFSET, END}, .handler = &VirtualMachine::insn_jmp},
	{.opcode = 33, .operand = {OP_INFO, OP_INFO, END}, .handler = &VirtualMachine::insn_cmp},
	{.opcode = 34, .operand = {IMM_INT, END}, .handler = &VirtualMachine::insn_alloc},
    {.opcode = 35, .operand = {END}, .handler = &VirtualMachine::insn_enter},
    {.opcode = 36, .operand = {END}, .handler = &VirtualMachine::insn_leave},
	{.opcode = 37, .operand = {IMM_SHORT, END}, .handler = &VirtualMachine::insn_vmcall},
	0
};
Runtime *VirtualMachine::get_parent()
{
	return this->parent;
}
void VirtualMachine::read_mem(uint32_t addr, uint32_t size, void *out)
{
	if(addr + size > this->max_space)
	{
		this->status = INVALID_ADDRESS;
		return;
	}
	memcpy(out, &this->memory[addr], size);
}

void VirtualMachine::write_mem(uint32_t addr, uint32_t size, void *in)
{
	if(addr + size > this->max_space)
	{
		this->status = INVALID_ADDRESS;
		return;
	}
	memcpy(&this->memory[addr], in, size);
}

uint8_t VirtualMachine::read_byte(uint32_t addr)
{
	uint8_t result;
	this->read_mem(addr, 1, &result);
	return result;
}
uint16_t VirtualMachine::read_short(uint32_t addr)
{
	uint16_t result;
	this->read_mem(addr, 2, &result);
	return result;
}
uint32_t VirtualMachine::read_int(uint32_t addr)
{
	uint32_t result;
	this->read_mem(addr, 4, &result);
	return result;
}
uint64_t VirtualMachine::read_long(uint32_t addr)
{
	uint64_t result;
	this->read_mem(addr, 8, &result);
	return result;
}
void VirtualMachine::write_byte(uint32_t addr, uint8_t data)
{
	this->write_mem(addr, 1, &data);
}
void VirtualMachine::write_short(uint32_t addr, uint16_t data)
{
	this->write_mem(addr, 2, &data);
}
void VirtualMachine::write_int(uint32_t addr, uint32_t data)
{
	this->write_mem(addr, 4, &data);
}
void VirtualMachine::write_long(uint32_t addr, uint64_t data)
{
	this->write_mem(addr, 8, &data);
}
Descriptor *VirtualMachine::decode(uint64_t *operand_out, uint32_t max_operand_size)
{
	uint8_t opcode = this->read_byte(this->pc);
    DBG_PRINT("[!] opcode-> %02x\n", opcode);
	if(this->status)
		return nullptr;
	
	this->pc += 1;
	Descriptor *desc = &table[opcode];
	if(desc->opcode == opcode)
	{
		uint8_t *operand_desc = desc->operand;
		uint64_t idx = 0;
		while(*operand_desc != END)
		{
			uint8_t type = *operand_desc;
			if(type == IMM_BYTE || type == OP_INFO)
			{
				operand_out[idx++] = this->read_byte(this->pc);
                DBG_PRINT("[!] \toperand -> [%02x]\n", operand_out[idx - 1]);
				this->pc += 1;
			}
			else if(type == IMM_SHORT)
			{
				operand_out[idx++] = this->read_short(this->pc);
                DBG_PRINT("[!] \toperand -> [%04x]\n", operand_out[idx - 1]);
				this->pc += 2;
			}
			else if(type == IMM_INT || type == OFFSET)
			{
				operand_out[idx++] = this->read_int(this->pc);
                DBG_PRINT("[!] \toperand -> [%08x]\n", operand_out[idx - 1]);
				this->pc += 4;
			}
			else if(type == IMM_LONG)
			{
				operand_out[idx++] = this->read_long(this->pc);
                DBG_PRINT("[!] \toperand -> [%016llx]\n", operand_out[idx - 1]);
				this->pc += 8;
			}
			else
			{
				this->status = INVALID_FORMAT;
                DBG_LOG("[!] invalid operand\n");
			}
			if(this->status)
				return nullptr;
			if(idx >= max_operand_size)
			{
				this->status = BUFFER_OVER_FLOW;
                DBG_LOG("[!] buffer overflow\n");
				return nullptr;
			}
			operand_desc++;
		}
		return desc;
	}
	else
	{
		this->status = INVALID_OPCODE;
        DBG_LOG("[!] invalid opcode\n");
		return nullptr;
	}
		
}
bool VirtualMachine::run_step()
{
	uint64_t operands[3];
    DBG_PRINT("[!] status: %d\n", this->status);
	DBG_PRINT("[!] pc-> %x\n", this->get_pc());
    DBG_PRINT("[!] rbp-> %d rsp -> %d\n", this->get_runtime_stack()->get_rbp(), this->get_runtime_stack()->get_rsp());
	Descriptor *desc = this->decode(operands, 3);
	if(this->status)
	{
		DBG_LOG("[!] instruction decode error");
		return 0;
	}
	
	desc->handler(*this, operands);
	
	if(this->status)
    {
        DBG_PRINT("\n\n[+] virtual machine exit abnormally from %x...\n", this->get_pc());
        return 0;
    }
		
	if(this->pc == -1)
	{
		DBG_LOG("\n\n[+] virtual machine exit successfully...");
		this->status = EXIT_NORMALLY;
		return 0;
	}
	if(this->pc >= this->max_space)
	{
		this->status = INVALID_PC;
		DBG_LOG("[!] invalid memory space for pc");
		return 0;
	}
	return 1;
}
void VirtualMachine::run_init()
{
	this->status = 0;
	uint64_t exit = -1;
	this->runtime_stack->push(&exit, 8);
    DBG_PRINT("[!] run init!!! rbp-> %d rsp -> %d\n", this->get_runtime_stack()->get_rbp(), this->get_runtime_stack()->get_rsp());
	std::map<std::string, BuiltinModule*>::iterator iter;
	for(iter = this->modules.begin(); iter != this->modules.end(); iter++)
		iter->second->on_vm_start(this); 
}
void VirtualMachine::run_end()
{
	std::map<std::string, BuiltinModule*>::iterator iter;
	for(iter = this->modules.begin(); iter != this->modules.end(); iter++)
		iter->second->on_vm_exit(this);
}
void VirtualMachine::run()
{
	if(!this->loaded)
	{
		this->status = FILE_NOT_LOAD;
		return;
	}
	
	this->run_init();
	bool r = true;
	while(r)
		r  = this->run_step();
	this->run_end();
	 
}

VirtualMachine *VirtualMachine::fork()
{
	if(!this->loaded)
		return nullptr;
	VirtualMachine *other = new VirtualMachine(this->parent, this->bytecode);
	std::map<std::string, BuiltinModule*>::iterator iter;
	for(iter = this->modules.begin(); iter != this->modules.end(); iter++)
		other->add_module(iter->second->clone());
	other->load();
	if(!other->loaded)
		return nullptr;
	if(other->max_space != this->max_space)
	{
		delete other;
		return nullptr;
	}
	other->pc = this->pc;
	other->status = this->status;
	
	
	if(!other->op_stack->clone(this->op_stack) || !other->runtime_stack->clone(this->runtime_stack))
	{
		delete other;
		return nullptr;
	}
	memcpy(other->memory, this->memory, other->max_space);
	return other; 
	
}

Runtime::Runtime()
{
	this->add_module(new OsModule());
}
VirtualMachine *Runtime::add_uninitialized_process(BytecodeFile *bytecode)
{
    if(!bytecode->is_loaded())
        return nullptr;
	VirtualMachine *process = new VirtualMachine(this, bytecode);
	std::map<std::string, BuiltinModule*>::iterator iter;
	for(iter = this->modules.begin(); iter != this->modules.end(); iter++)
		process->add_module(iter->second->clone());
	process->load();
	if(process->loaded)
	{
		this->ready.push_back(process);
		return process;
	}
		
	else
	{
		delete process;
		return nullptr;
	}
		
	
}
Runtime::~Runtime()
{
    DBG_LOG("[!] deallocate object<VirtualMachine>");
	for(std::vector<VirtualMachine*>::iterator iter = this->finish.begin(); iter != this->finish.end(); iter++)
		delete *iter;
	for(std::vector<VirtualMachine*>::iterator iter = this->ready.begin(); iter != this->ready.end(); iter++)
		delete *iter;
    DBG_LOG("[!] deallocate object<SharedMemory>");
	for(std::map<uint32_t, std::pair<uint8_t*, size_t>>::iterator iter = this->shared_mem.begin(); iter != this->shared_mem.end(); iter++)
		delete iter->second.first;
    DBG_LOG("[!] deallocate object<BuiltinModuleZygote>");
	std::map<std::string, BuiltinModule*>::iterator iter;
	for(iter = this->modules.begin(); iter != this->modules.end(); iter++)
		delete iter->second;
}
void Runtime::add_forked_process(VirtualMachine *process)
{
	this->ready.push_back(process);
}
void Runtime::start()
{
	for(VirtualMachine *ps : this->ready)
		ps->run_init();
		
	DBG_LOG("[!] execution start.......\n"); 
	while(this->ready.size() != 0)
	{
		DBG_PRINT("[!] ready count: %lld, wait count: %lld, kill count %lld\n", this->ready.size(), this->wait.size(), this->finish.size());
		std::vector<VirtualMachine*> kill;
        std::vector<VirtualMachine*> execute(this->ready);
		for(VirtualMachine *ps : execute)
		{
			if(!ps->run_step())
			{
				DBG_PRINT("[+] exit status %d\n", ps->get_status());
				kill.push_back(ps);
			}
				
		}
		
		for(VirtualMachine *ps : kill)
			ps->run_end();
		
		for(VirtualMachine * ps : kill)
		{
			std::vector<VirtualMachine*>::iterator pos = std::find(this->ready.begin(), this->ready.end(), ps);
			if(pos != this->ready.end())
				this->ready.erase(pos);
			this->finish.push_back(ps);
		}
	}
	DBG_LOG("[!] execution finish.......\n"); 
}
VirtualMachine *Runtime::new_process(BytecodeFile *bytecode)
{
	VirtualMachine *vm = this->add_uninitialized_process(bytecode);
	return vm;
		
}
void Runtime::clear()
{
	for(std::vector<VirtualMachine*>::iterator iter = this->finish.begin(); iter != this->finish.end(); iter++)
		delete *iter;
	this->finish.clear();
}
void Runtime::add_module(BuiltinModule *module)
{
	this->modules[module->get_name()] = module; 
}
VirtualMachine *Runtime::get_ps(uint32_t pid)
{
	for(VirtualMachine *ps : this->ready)
		if(ps->get_pid() == pid)
			return ps;
	
	for(VirtualMachine *ps : this->wait)
		if(ps->get_pid() == pid)
			return ps;
	return nullptr;
}
uint32_t Runtime::new_shared_memory(size_t size)
{
	uint32_t md = rand();
	while(this->shared_mem.find(md) != this->shared_mem.end() && md != 0)
		md = rand();
	uint8_t *addr = (uint8_t*)malloc(size);
	this->shared_mem[md] = std::pair<uint8_t*, size_t>(addr, size);
	if(addr == nullptr)
		return 0;
	return md;
}
bool Runtime::set_wait(VirtualMachine *ps)
{
	for(std::vector<VirtualMachine*>::iterator iter = this->ready.begin(); iter != this->ready.end(); iter++)
	{
		if(*iter == ps)
		{
			this->ready.erase(iter);
			this->wait.push_back(ps);
			return true;
		}
	}
	return false;
}
bool Runtime::set_wake(VirtualMachine *ps)
{
	for(std::vector<VirtualMachine*>::iterator iter = this->wait.begin(); iter != this->wait.end(); iter++)
	{
		if(*iter == ps)
		{
			this->wait.erase(iter);
			this->ready.push_back(ps);
			return true;
		}
	}
	return false;
}
std::vector<VirtualMachine*> &Runtime::get_ready()
{
    return this->ready;
}
std::vector<VirtualMachine*> &Runtime::get_wait()
{
    return this->wait;
}
std::vector<VirtualMachine*> &Runtime::get_finish()
{
    return this->finish;
}
void Runtime::free_shared_memory(uint32_t md)
{
	if(this->shared_mem.find(md) != this->shared_mem.end())
	{
		free(this->shared_mem[md].first);
		this->shared_mem.erase(md);
	}
	
}
bool Runtime::read_shared_memory(uint32_t md, size_t size, uint64_t offset, uint8_t *buf)
{
	if(this->shared_mem.find(md) == this->shared_mem.end())
		return false;
	uint8_t *addr = this->shared_mem[md].first;
	size_t msize = this->shared_mem[md].second;
	if(offset + size > msize)
		return false;
	memcpy(buf, &addr[offset], size);
	return true;
}
bool Runtime::write_shared_memory(uint32_t md, size_t size, uint64_t offset, uint8_t *buf)
{
	if(this->shared_mem.find(md) == this->shared_mem.end())
		return false;
	uint8_t *addr = this->shared_mem[md].first;
	size_t msize = this->shared_mem[md].second;
	if(offset + size > msize)
		return false;
	memcpy(&addr[offset], buf, size);
	return true;
}
