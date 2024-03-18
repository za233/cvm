int add(int a,int b)
{
	return a+b;
}
char num_char[17] = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
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
int main()
{
    int sum = 0, i;
    for(i = 0; i < 100; i+= 1)
    {
        sum += i;
    }
    print_oct(sum);
	return add(sum,2);
}
  
