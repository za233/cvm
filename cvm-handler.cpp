#include"cvm-vm.h"

void VirtualMachine::insn_invalid(uint64_t *operands)
{
	this->status = INVALID_OPCODE;
}
void VirtualMachine::insn_vmcall(uint64_t *operands)
{
	uint16_t index = operands[0];
	if(index < this->call_table.size())
	{
		std::pair<BuiltinModule&, VM_CALL_FUNC> p = this->call_table[index];
		VM_CALL_FUNC ptr = p.second; 
		(p.first.*ptr)(this);
		return;
	}
	this->status = NO_SUCH_PROC;
}
void VirtualMachine::insn_load(uint64_t *operands)
{
	uint64_t addr = this->op_stack->pop();
	uint32_t load_size = operands[0];
	if(load_size == 1)
	{
		uint8_t data = this->read_byte(addr);
		this->op_stack->push(data);
	}
	else if(load_size == 2)
	{
		uint16_t data = this->read_short(addr);
		this->op_stack->push(data);
	}
	else if(load_size == 4)
	{
		uint32_t data = this->read_int(addr);
		this->op_stack->push(data);
		
	}
	else if(load_size == 8)
	{
		uint64_t data = this->read_long(addr);
		this->op_stack->push(data);
	}
	else
	{
		this->status = INVALID_FORMAT;
	}
	
}
void VirtualMachine::insn_store(uint64_t *operands)
{
	uint32_t load_size = operands[0];
	if(load_size == 1)
	{
		uint8_t data = this->op_stack->pop();
		this->write_byte(this->op_stack->pop(), data);
	}
	else if(load_size == 2)
	{
		uint16_t data = this->op_stack->pop();
		this->write_short(this->op_stack->pop(), data);
	}
	else if(load_size == 4)
	{
		uint32_t data = this->op_stack->pop();
		uint32_t addr = this->op_stack->pop();
		this->write_int(addr, data);
	}
	else if(load_size == 8)
	{
		uint64_t data = this->op_stack->pop();
		this->write_long(this->op_stack->pop(), data);
	}
	else
	{
		this->status = INVALID_FORMAT;
	}
}
void VirtualMachine::insn_push_imm1(uint64_t *operands)
{
	uint8_t imm = operands[0];
	this->op_stack->push(imm);
}
void VirtualMachine::insn_push_imm2(uint64_t *operands)
{
	uint16_t imm = operands[0];
	this->op_stack->push(imm);
}
void VirtualMachine::insn_push_imm4(uint64_t *operands)
{
	uint32_t imm = operands[0];
	this->op_stack->push(imm);
}
void VirtualMachine::insn_push_imm8(uint64_t *operands)
{
	uint64_t imm = operands[0];
	this->op_stack->push(imm);
}
void VirtualMachine::insn_push_bp(uint64_t *operands)
{
	this->op_stack->push(this->runtime_stack->get_rbp());
}
void VirtualMachine::insn_pop(uint64_t *operands)
{
	uint8_t times = operands[0];
	for(uint8_t i = 0; i < times; i++)
		this->op_stack->pop();
}
void VirtualMachine::insn_add(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->add(width);
}
void VirtualMachine::insn_sub(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->sub(width);
}
void VirtualMachine::insn_smul(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->mul(width, true);
}
void VirtualMachine::insn_umul(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->mul(width, false);
}
void VirtualMachine::insn_sdiv(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->div(width, true);
}
void VirtualMachine::insn_udiv(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->div(width, false);
}
void VirtualMachine::insn_smod(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->mod(width, true);
}
void VirtualMachine::insn_umod(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->mod(width, false);
}
void VirtualMachine::insn_and(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->_and(width);
}
void VirtualMachine::insn_or(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->_or(width);
}
void VirtualMachine::insn_xor(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->_xor(width);
}
void VirtualMachine::insn_shl(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->shl(width);
}
void VirtualMachine::insn_lshr(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->lshr(width);
}
void VirtualMachine::insn_ashr(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->ashr(width);
}
void VirtualMachine::insn_neg(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->neg(width);
}
void VirtualMachine::insn_not(uint64_t *operands)
{
	uint8_t width = operands[0];
	this->op_stack->_not(width);
}
void VirtualMachine::insn_sext(uint64_t *operands)
{
	uint8_t from_size = operands[0], to_size = operands[1];
	this->op_stack->sext(from_size, to_size);
}
void VirtualMachine::insn_st_arg(uint64_t *operands)
{
	uint64_t addr = this->op_stack->pop();
	this->runtime_stack->push(&addr, 8);
}
void VirtualMachine::insn_pop_arg(uint64_t *operands)
{
	uint8_t count = operands[0];
	for(uint8_t i = 0; i < count; i++)
		this->runtime_stack->pop(nullptr, 8);
}
void VirtualMachine::insn_call(uint64_t *operands)
{
	uint64_t addr = this->pc;
	int32_t offset = operands[0];
	this->runtime_stack->push(&addr, 8);
	this->pc += offset;
}
void VirtualMachine::insn_enter(uint64_t *operands)
{
    this->runtime_stack->enter();
}
void VirtualMachine::insn_leave(uint64_t *operands)
{
    this->runtime_stack->leave();
}
void VirtualMachine::insn_ret(uint64_t *operands)
{
	if(this->op_stack->get_top() < 1)
	{
		this->status = STACK_UNBALANCE;
		return;
	}
	uint64_t addr;
	this->runtime_stack->pop(&addr, 8);
	this->pc = addr;
}
void VirtualMachine::insn_br(uint64_t *operands)
{
	int32_t offset = operands[0];
	uint64_t flag = this->op_stack->pop();
	if(flag)
		this->pc += offset;
}
void VirtualMachine::insn_jmp(uint64_t *operands)
{
	int32_t offset = operands[0];
	this->pc += offset;
}

void VirtualMachine::insn_cmp(uint64_t *operands)
{
	uint8_t size = operands[0];
	uint8_t cmp = operands[1]; 
    
	this->op_stack->cmp(size, cmp);
}
void VirtualMachine::insn_alloc(uint64_t *operands)
{
	uint32_t size = operands[0]; 
	this->runtime_stack->alloc_space(size);
}
