; 64 bit code.
bits 64
	; to use the printf() function.
extern _printf

; data section.
section .data
	fmt:    db "exit code:%d", 10, 0 ; the format string for the exit message.

; text section
section .text
	global _main ; the entry point.

; the subroutine for sys-exit. rax will be the exit code.
exit_program#:
	and rsp, 0xFFFFFFFFFFFFFFF0 ; stack must be 16 bytes aligned to call a C function.
	push rax ; we need to preserve rax here.
	push rax ; pushing twice for 16 byte alignment. We'll discard this later. 

	; call printf to print out the exti code.
	lea rdi, [rel fmt] ; the format string
	mov rsi, rax		; the exit code 
	mov rax, 0			; no xmm register is used.
	call _printf

	pop rax ; this value will be discared (as we did 'push rax' twice for 16 bytes alignment.

	mov rax, 0x2000001; specify the exit sys call.
	pop rdi ; this is the rax value we pushed at the entry of this sub routine
	syscall ; exit!

_main:
	mov rax, 0 ; initialize the accumulator register.
	mov rax, 27
	push rax
	mov rax, 4
	mov rbx, rax
	pop rax
	mov rdx, 0
	idiv rbx
	mov rax, rdx

	jmp exit_program# ; exit the program, rax should hold the exit code.
