; CS2208 assignment3 question 2
; Wenhan Sun wsun228 251020850

		AREA question2, CODE, READONLY
		ENTRY
		MOV		r0, #1						; instruction 1 : put #1 in register r0 to assume the string is palindrome
		ADR		r1, STRING - 1				; instruction 2 : register r1 points at 1 byte prior to the begining of the string
		ADR		r2, EoS						; instruction 3 : register r2 points at the end of string
Prefix	LDRB	r3, [r1, #1]!				; instruction 4 : load the next byte of the string from the start to register r3
		CMP		r3, #90						; instruction 5 : compare the ASCII of register r3 with #90(Z)
		SUBGT	r3, r3, #32					; instruction 6 : if larger than #90(Z), subtract #32 from register r3 to avoid small case letter
		CMP		r3, #65						; instruction 7 : compare the ASCII of register r3 with #65(A)
		BLT		Prefix						; instruction 8 : if smaller than #65(A), goto lable Prefix to load the next byte
		CMP		r3, #90						; instruction 9 : compare the ASCII of register r3 with #90(Z)
		BGT		Prefix						; instruction 10: if larger than #90(Z), goto lable Prefix to load the next byte
Suffix	LDRB	r4, [r2, #-1]!				; instruction 11: load the previous byte of the string from the end to register r4
		CMP		r4, #90						; instruction 12: compare the ASCII of register r4 with #90(Z)
		SUBGT	r4, r4, #32					; instruction 13: if larger than #90(Z), subtract 32 from register r4 to avoid small case letter
		CMP		r4, #65						; instruction 14: compare the ASCII of register r4 with #65(A)
		BLT		Suffix						; instruction 15: if smaller than #65(A), goto lable Prefix to load the previous byte
		CMP		r4, #90						; instruction 16: compare the ASCII of register r4 with #90(Z)
		BGT		Suffix						; instruction 17: if larger than #90(Z), goto lable Suffix to load the previous byte
		CMP		r3, r4						; instruction 18: compare the ASCII of register r3 and r4
		MOVNE	r0, #2						; instruction 19: if the letters are not the same, put #2 in register r0, and the string is not palindrome
		CMP		r1, r2						; instruction 20: compare the current address stored in register r1 and r2
		BLT		Prefix						; instruction 21: if start index is less than end index, goto lable Prefix for a new search

		AREA question2, DATA, READONLY
STRING 	DCB		"He lived as a devil, eh?"	; string
EoS    	DCB		0x00						; end of string
		END
