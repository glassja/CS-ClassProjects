#lang racket

(define (make_list_of_size n e)
  (cond ((= n 0) '())
        (else (cons e (make_list_of_size (- n 1) e)))))

(define (zeros lst)
  (cond
    ((null? lst) 0)
    (else
     (if (equal? (car lst) 0) (+ 1 (zeros (cdr lst))) (zeros (cdr lst))))))

(define (power a b)
  (cond ((= b 0) 1)
        ((negative? b) (power (/ 1 a) (* -1 b)))
        (else (* a (power a (- b 1))))))

(define (remove lst atm)
  (cond
    ((null? lst) '())
    ((not (equal? (car lst) atm)) (cons (car lst) (remove (cdr lst) atm)))
    (else (remove (cdr lst) atm))))

(define (reverse lst)
  (cond
    ((null? lst) null)
    (else (append (reverse (cdr lst)) (list (car lst))))))