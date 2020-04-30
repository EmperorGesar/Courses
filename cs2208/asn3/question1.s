      	AREA 	question1, CODE, READONLY
      	ENTRY
      	ADR	r0, UPC - 1		  	; instruction 1 : register r0 points at 1 byte prior to the begining of the UPC string
      	MOV	r1, #6			    	; instruction 2 : put #6 in register r1 as loop counter
Loop	LDRB	r2, [r0, #1]!	 		; instruction 3 : load the next odd index byte of the string to register r2
      	LDRB	r3, [r0, #1]!	 		; instruction 4 : load the next even index byte of the string to register r3
      	SUB	r2, r2, #48		  	; instruction 5 : subtract #48 from the register r2 to get the true digit from the ASCII
      	SUB	r3, r3, #48		  	; instruction 6 : subtract #48 from the register r3 to get the true digit from the ASCII
      	ADD	r2, r2, r2, LSL #1		; instruction 7 : multiply 3 times digit with odd index stored in register r2 by adding 
                                  		;                 with #1 bit left shift
      	ADD	r4, r4, r2		  	; instruction 8 : add the product stored in register r2 to register r4 with the sum of odd 
						; 		  index digit
      	ADD	r5, r5, r3		  	; instruction 9 : add digit with even index stored in register r3 to register r5 
                                  		;                 with the sum of even index digit
      	SUBS	r1, r1, #1		  	; instruction 10: decrease loop counter register r1 by #1, set flags
      	BNE	Loop			      	; instruction 11: continue loop until counter register r1 is #0
      	ADD	r4, r4, r5			; instruction 12: add the sum of digit with both odd and even index stored in register r4 
						;		  and r4 to r5
Mod	CMP	r4, #10				; instruction 13: mod 10 by subtraction, first compare the sum stored in register r4 with #10
      	SUBGE	r4, r4, #10			; instruction 14: if larger than #10, subtract #10 from register r4
      	BGT	Mod				; instruction 15: if the remainder larger than #0, goto label Mod10 for next step of division, 
                                  		;                 and if less than #10, break
      	MOV	r0, #1			    	; instruction 16: put #1 in register r0 to assume the string code is valid
      	CMP	r4, #0				; instruction 17: compare the remainder stored in register r4 with #0
      	MOVNE	r0, #2				; instruction 18: if the remainder is not #0, put #2 in register r0, and the string code 
						; 		  is not valid
		
      	AREA 	question1, DATA, READONLY
UPC	DCB	"013800150738"			; UPC string
      	END
