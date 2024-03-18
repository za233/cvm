
#include"cvm-stack.h"
#include<cstdio>
uint64_t get_mask(int size)
{
	uint64_t mask = -1; 
	return mask >> ((8 - (size % 8)) * 8);
}
RuntimeStack::RuntimeStack(ErrorHandler *host)
{
    this->host = host;
}
uint8_t *RuntimeStack::top(int delta)
{
	return &this->stack[this->rsp + delta];
}
void RuntimeStack::set_memory(uint8_t *memory, uint64_t max_space)
{
    this->stack = memory;
    this->max_space = max_space;
}
uint8_t *RuntimeStack::get_memory()
{
    return this->stack;
}
void RuntimeStack::set_rsp(uint64_t rsp)
{
    this->rsp = rsp;
}
void RuntimeStack::set_rbp(uint64_t rbp)
{
    this->rbp = rbp;
}
uint64_t RuntimeStack::get_rsp()
{
	return this->rsp;
}
uint64_t RuntimeStack::get_rbp()
{
	return this->rbp;
}
void RuntimeStack::push(void *ptr, int size)
{
    uint64_t addr = this->alloc_space(size);
    memcpy(&this->stack[addr], ptr, size);
}
void RuntimeStack::pop(void *ptr, int size)
{
    uint64_t addr = this->free_space(size);
    if(ptr != nullptr) 
    	memcpy(ptr, &this->stack[addr], size);
}
uint64_t RuntimeStack::alloc_space(int size)
{
    this->rsp -= size;
    if(this->rsp < 0)
    {
        this->host->stack_overflow();
        return 0;
    }
    memset(&this->stack[this->rsp], 0, size);
    return this->rsp;
}
uint64_t RuntimeStack::free_space(int size)
{
    uint64_t addr = this->rsp;
    this->rsp += size;
	if(this->rsp > this->max_space)
    {
        this->host->stack_underflow();
        return 0;
    }
    return addr;
}
void RuntimeStack::enter()
{
    this->push(&this->rbp, 8);
    this->rbp = this->rsp;
}
void RuntimeStack::leave()
{
    this->rsp = this->rbp;
    this->pop(&this->rbp, 8);
}

OperationStack::OperationStack(ErrorHandler *host)
{
	this->host = host;
}
uint64_t OperationStack::get_top()
{
	return this->top;
}
void OperationStack::set_memory(uint64_t *memory, uint64_t max_space)
{
    this->stack = memory;
    this->max_space = max_space;
}
uint64_t *OperationStack::get_memory()
{
	return this->stack;
}
uint64_t OperationStack::pop()
{
	if(this->top <= 0)
	{
		this->host->stack_underflow();
		return 0;
	}
	return this->stack[--this->top];
}
void OperationStack::push(uint64_t data)
{
	if(this->top >= this->max_space)
	{
		this->host->stack_overflow();
		return;
	}
	this->stack[top++] = data;
}
void OperationStack::add(int size)
{
	uint64_t r = this->pop();
	uint64_t l = this->pop();
	uint64_t mask = get_mask(size);
	this->push(((l & mask) + (r & mask)) & mask);
}
void OperationStack::sub(int size)
{
	uint64_t r = this->pop();
	uint64_t l = this->pop();
	uint64_t mask = get_mask(size);
	this->push(((l & mask) - (r & mask)) & mask);
}

void OperationStack::mul(int size, bool sign)
{
	uint64_t r = this->pop();
	uint64_t l = this->pop();
	if(sign)
	{
        if(size == 1)
        {
            int8_t t0 = l, t1 = r;
            int8_t res = t0 * t1;
		    this->push(res);
        }
        else if(size == 2)
        {
            int16_t t0 = l, t1 = r;
            int16_t res = t0 * t1;
		    this->push(res);
        }
        else if(size == 4)
        {
            int32_t t0 = l, t1 = r;
            int32_t res = t0 * t1;
		    this->push(res);
        }
        else
        {
            int64_t t0 = l, t1 = r;
            int64_t res = t0 * t1;
		    this->push(res);
        }
		
	}
	else
	{
		if(size == 1)
        {
            uint8_t t0 = l, t1 = r;
            uint8_t res = t0 * t1;
		    this->push(res);
        }
        else if(size == 2)
        {
            uint16_t t0 = l, t1 = r;
            uint16_t res = t0 * t1;
		    this->push(res);
        }
        else if(size == 4)
        {
            uint32_t t0 = l, t1 = r;
            uint32_t res = t0 * t1;
		    this->push(res);
        }
        else
        {
            uint64_t t0 = l, t1 = r;
            uint64_t res = t0 * t1;
		    this->push(res);
        }
	}
	
}
void OperationStack::div(int size, bool sign)
{
	uint64_t r = this->pop();
	uint64_t l = this->pop();
	uint64_t mask = get_mask(size);
	if(!(r & mask))
	{
		this->host->divide_by_zero();
		return;
	}
	if(sign)
	{
        if(size == 1)
        {
            int8_t t0 = l, t1 = r;
            int8_t res = t0 / t1;
		    this->push(res);
        }
        else if(size == 2)
        {
            int16_t t0 = l, t1 = r;
            int16_t res = t0 / t1;
		    this->push(res);
        }
        else if(size == 4)
        {
            int32_t t0 = l, t1 = r;
            int32_t res = t0 / t1;
		    this->push(res);
        }
        else
        {
            int64_t t0 = l, t1 = r;
            int64_t res = t0 / t1;
		    this->push(res);
        }
		
	}
	else
	{
		if(size == 1)
        {
            uint8_t t0 = l, t1 = r;
            uint8_t res = t0 / t1;
		    this->push(res);
        }
        else if(size == 2)
        {
            uint16_t t0 = l, t1 = r;
            uint16_t res = t0 / t1;
		    this->push(res);
        }
        else if(size == 4)
        {
            uint32_t t0 = l, t1 = r;
            uint32_t res = t0 / t1;
		    this->push(res);
        }
        else
        {
            uint64_t t0 = l, t1 = r;
            uint64_t res = t0 / t1;
		    this->push(res);
        }
	}
	
}
void OperationStack::mod(int size, bool sign)
{
	uint64_t r = this->pop();
	uint64_t l = this->pop();
	uint64_t mask = get_mask(size);
	if(!(r & mask))
	{
		this->host->divide_by_zero();
		return;
	}
	if(sign)
	{
        if(size == 1)
        {
            int8_t t0 = l, t1 = r;
            int8_t res = t0 % t1;
		    this->push(res);
        }
        else if(size == 2)
        {
            int16_t t0 = l, t1 = r;
            int16_t res = t0 % t1;
		    this->push(res);
        }
        else if(size == 4)
        {
            int32_t t0 = l, t1 = r;
            int32_t res = t0 % t1;
		    this->push(res);
        }
        else
        {
            int64_t t0 = l, t1 = r;
            int64_t res = t0 % t1;
		    this->push(res);
        }
		
	}
	else
	{
		if(size == 1)
        {
            uint8_t t0 = l, t1 = r;
            uint8_t res = t0 % t1;
		    this->push(res);
        }
        else if(size == 2)
        {
            uint16_t t0 = l, t1 = r;
            uint16_t res = t0 % t1;
		    this->push(res);
        }
        else if(size == 4)
        {
            uint32_t t0 = l, t1 = r;
            uint32_t res = t0 % t1;
		    this->push(res);
        }
        else
        {
            uint64_t t0 = l, t1 = r;
            uint64_t res = t0 % t1;
		    this->push(res);
        }
	}
	
}
void OperationStack::_xor(int size)
{
	uint64_t r = this->pop();
	uint64_t l = this->pop();
	uint64_t mask = get_mask(size);
	this->push(((l & mask) ^ (r & mask)) & mask);
}
void OperationStack::_or(int size)
{
	uint64_t r = this->pop();
	uint64_t l = this->pop();
	uint64_t mask = get_mask(size);
	this->push(((l & mask) | (r & mask)) & mask);
}
void OperationStack::_and(int size)
{
	uint64_t r = this->pop();
	uint64_t l = this->pop();
	uint64_t mask = get_mask(size);
	this->push(((l & mask) & (r & mask)) & mask);
}
void OperationStack::_not(int size)
{
	uint64_t l = this->pop();
	uint64_t mask = get_mask(size);
	this->push((~(l & mask)) & mask);
}
void OperationStack::neg(int size)
{
	uint64_t l = this->pop();
	uint64_t mask = get_mask(size);
	this->push((-(l & mask)) & mask);
}
bool OperationStack::_sext(int old_size, int new_size, uint64_t &data)
{
	uint64_t mask = (get_mask(new_size) >> (old_size * 8)) << (old_size * 8);
	if(new_size < old_size)
    	return false;
    data &= get_mask(new_size);
    uint64_t flag = data & (1 << (old_size * 8 - 1));
    if(flag)
    {   
        uint64_t mask = (get_mask(new_size) >> (old_size * 8)) << (old_size * 8);
        data |= mask;
        return true;
    }   
    else
    	return true;
}
void OperationStack::sext(int old_size, int new_size)
{
    uint64_t l = this->pop();
    if(this->_sext(old_size, new_size, l))
    	this->push(l);
    else
    	this->host->invalid_insn();
}
void OperationStack::shl(int size)
{
	uint64_t r = this->pop();
	uint64_t l = this->pop();
	uint64_t mask = get_mask(size);
	this->push(((l & mask) << (r & mask)) & mask);
}
void OperationStack::lshr(int size)
{
	uint64_t r = this->pop();
	uint64_t l = this->pop();
	uint64_t mask = get_mask(size);
	this->push(((l & mask) >> (r & mask)) & mask);
}
void OperationStack::ashr(int size)
{
	uint64_t mask = get_mask(size);
	uint64_t r = this->pop() & mask;
	int64_t l = this->pop() & mask;
	this->push((l >> r) & mask);
}
uint8_t EQ = 0, NEQ = 1, LE = 2, LT = 3, GE = 4, GT = 5, S_LE = 6, S_LT = 7, S_GE = 8, S_GT = 9;
void OperationStack::cmp(int size, int type)
{
	uint64_t mask = get_mask(size);
	uint64_t r = this->pop() & mask;
	uint64_t l = this->pop() & mask;
	if(type == EQ)
		this->push(l == r);
	else if(type == NEQ)
		this->push(l != r);
	else if(type == LE)
		this->push(l <= r);
	else if(type == LT)
		this->push(l < r);	
	else if(type == GE)
		this->push(l >= r);
	else if(type == GT)
		this->push(l > r);	
	else
	{
		
		if(!this->_sext(size, 8, l) || !this->_sext(size, 8, r))
		{
			this->host->invalid_insn();
			return;
		}
		int64_t sl = l;
		int64_t sr = r;
		if(type == S_LT)
			this->push(sl < sr);
		else if(type == S_LE)
			this->push(sl <= sr);
		else if(type == S_GT)
			this->push(sl > sr);
		else if(type == S_GE)
			this->push(sl >= sr);
		else
			this->host->invalid_insn();

	}
	
}
bool OperationStack::clone(OperationStack *other)
{
	if(this->max_space != other->max_space)
		return false;
	for(uint32_t i = 0; i < other->top; i++)
		this->push(other->stack[i]);
	return true;
}
bool RuntimeStack::clone(RuntimeStack *other)
{
	if(this->max_space != other->max_space)
		return false;
	this->rsp = other->rsp;
	this->rbp = other->rbp;
	memcpy(this->stack, other->stack, this->max_space);
	return true;
		
}
