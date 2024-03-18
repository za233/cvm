#include<cstdint>
#include<cstring>
class ErrorHandler
{
public:
    virtual void stack_overflow() = 0;
    virtual void stack_underflow() = 0;
    virtual void divide_by_zero() = 0;
    virtual void invalid_insn() = 0; 
};
class RuntimeStack
{
private:
	uint8_t *stack;
    uint64_t max_space;
	uint64_t rsp = 0, rbp = 0;
    ErrorHandler *host;
public:
	void set_rsp(uint64_t rsp);
	void set_rbp(uint64_t rbp);
	uint64_t get_rsp();
	uint64_t get_rbp();
    RuntimeStack(ErrorHandler *host);
    void set_memory(uint8_t *memory, uint64_t max_space);
    uint8_t * get_memory();
    uint8_t *top(int delta);
    void push(void *ptr, int size);
    void pop(void *ptr, int size);
	void enter();
	void leave();
	uint64_t alloc_space(int size);
	uint64_t free_space(int size);
	bool clone(RuntimeStack *other); 
};

class OperationStack
{
private:
	uint64_t *stack;
	uint64_t top = 0, max_space;
    ErrorHandler *host;
public:
	OperationStack(ErrorHandler *host);
    void set_memory(uint64_t *memory, uint64_t max_space);
	void push(uint64_t data);
	uint64_t pop();
	uint64_t * get_memory();
	void add(int size);
	void sub(int size);
	void mul(int size, bool sign);
	void div(int size, bool sign);
	void mod(int size, bool sign);
	void _xor(int size);
	void _or(int size);
	void _and(int size);
	void _not(int size);
	void neg(int size);
    void sext(int old_size, int new_size);
    void shl(int size);
    void lshr(int size);
    void ashr(int size);
    void cmp(int size, int type);
    bool _sext(int old_size, int new_size, uint64_t &data);
    uint64_t get_top();
    bool clone(OperationStack *other); 

};
