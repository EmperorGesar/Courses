; CS2208 assignment4 question 1
; Wenhan Sun wsun228 251020850

		AREA 	question1, CODE, READONLY
		ENTRY
		ADR		r1, STRING1						; instruction 1 :	register r1 points at the begining of string1
		ADR		r2, EoS							; instruction 2 :	register r2 points at the ending of string1
		ADR		r3, STRING2						; instruction 3 :	register r3 points at the begining of string2
		MOV		r4, r1							; instruction 4 :	register r4 points at the begining of string1 as temporary comparison pointer
		SUB		r5, r2, #3						; instruction 5 :	register r5 points at 3 bytes prior of the ending of string2
		CMP		r0, #0							; instruction 6 :	compare register r0 with #0 to set Z flag
		BEQ		Start							; instruction 7 :	branch to start comparison with 't'(0x74) since the pattern of single word 
												;					"the" might locate at the start of string1
Loop	SUB		r4, r1, #1						; instruction 8 :	register r4 trace back 1 byte prior from register r1 in string1 for comparison
		CMP		r4, r5							; instruction 9 :	compare temporary pointer register r4 with the address of 3 bytes prior of 
												;					the ending of string2 pointed by register r5
		BGE		Skip							; instruction 10:	if temporary pointer register r4 is greater or equals to the address, it means 
												;					the remaining length of string1 is not able to match the pattern "the" with 
												;					length 3, branch to skip the comparison processes
		LDRB	r0, [r4], #1					; instruction 11:	load the next byte from string1 pointed by temporary pointer register r4 to r0
		CMP		r0, #0x20						; instruction 12:	compare character in register r0 with ' '(0x20) to indicate start of word
Start	LDRBEQ	r0, [r4], #1					; instruction 13:	if current character is start of word, load the next byte from string1 pointed 
												;					by temporary pointer register r4 to r0
		CMPEQ	r0, #0x74						; instruction 14:	if the next byte is loaded, compare character in register r0 with 't'(0x74)
		LDRBEQ	r0, [r4], #1					; instruction 15:	if current character is 't'(0x74), load the next byte from string1 pointed by 
												;					temporary pointer register r4 to r0
		CMPEQ	r0, #0x68						; instruction 16:	if the next byte is loaded, compare character in register r0 with 'h'(0x68)
		LDRBEQ	r0, [r4], #1					; instruction 17:	if current character is 'h'(0x68), load the next byte from string1 pointed by 
												;					temporary pointer register r4 to r0
		CMPEQ	r0, #0x65						; instruction 18:	if the next byte is loaded, compare character in register r0 with 'e'(0x65)
		LDRBEQ	r0, [r4], #1					; instruction 19:	if current character is 'e'(0x65), load the next byte from string1 pointed by 
												;					temporary pointer register r4 to r0
		CMPEQ	r0, #0x20						; instruction 20:	if the next byte is loaded, compare character in register r0 with ' '(0x20)
		SUBEQ	r1, r4, #1						; instruction 21:	if current character is ' '(0x20), the pattern of single word "the" is matched 
												;					in string1, update register r1 to 1 byte prior of temporary pointer register r4 
												;					to include the last ' ' to store in string2
Skip	CMP		r4, r2							; instruction 22:	if Z flag is cleared during any of the comparison processes or skipped, 
												;					compare the temporary pointer register r4 with address of the ending of 
												;					string1 stored in register r2
		BGT		Exit							; instruction 23:	branch to exit if temporary pointer is greater than address of the ending 
												;					of string1
		LDRB	r0, [r1], #1					; instruction 24:	load the next byte of character from string1 pointed by register r1 to r0
		STRB	r0, [r3], #1					; instruction 25:	store character in register r0 to the next available byte of string2 pointed by 
												;					register r3
		CMP		r1, r2							; instruction 26:	compare the address of next loading byte stored in register r1 with the address 
												;					of the ending of string1 stored in register r2
Exit	BLT		Loop							; instruction 27:	if the current address does not meet the ending of the string, loop

		AREA	question1, DATA, READONLY
STRING1	DCB		"and the man said they must go"	; string1
EoS		DCB		0x00							; end of string1
STRING2	space	0x7F							; just allocating 127 bytes
		END
