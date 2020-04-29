#lang racket
;Quicksort algorithm

(define quicksort
  (lambda (L)
    (if (null? L)
        '()
        (letrec ((pre (lambda (rest)
                        (if (null? rest)
                            '()
                            (if (> (car L) (car rest))
                                (cons (car rest) (pre (cdr rest)))
                                (pre (cdr rest))))))
                 (post (lambda (rest)
                         (if (null? rest)
                             '()
                             (if (< (car L) (car rest))
                                 (cons (car rest) (post (cdr rest)))
                                 (post (cdr rest))))))
                 (combine (lambda (head tail)
                            (if (null? head)
                                tail
                                (cons (car head) (combine (cdr head) tail))))))
          (combine (combine (quicksort (pre L)) (list (car L))) (quicksort (post L)))))))

(quicksort '(5 3 7 2 1 6 4))
