#lang racket
;Joshua Glass
;CSCI 320
;Assignment 3
(define (length lst)
  (cond
    ((null? lst) 0)
    (else (+ 1 (length (cdr lst))))))

(define (permutation? list1 list2)
  (cond
    ((and (null? list1) (null? list2)) #t)
    ((not (eqv? (length list1) (length list2))) #f)
    ((not (member (car list1) list2)) #f)
    (else (member (car list1) list2) (permutation? (cdr list1) (remove (car list1) list2)))))

(define (partition compare lst)
  (cond
    ((null? lst) '())
    ((compare (car lst)) (cons (car lst) (partition compare (cdr lst))))
    (else (partition compare (cdr lst)))))

(define (quicksort lst)
  (cond
    ((null? lst) '())
    (else (let ((pivot (car lst)))
            (append (append (quicksort (partition (lambda (x) (< x pivot)) lst))
                            (partition (lambda (x) (= x pivot)) lst))
                    (quicksort (partition (lambda (x) (> x pivot)) lst)))))))

(define (remove-all atm lst)
  (cond
    ((not (member atm lst)) lst)
    ((member atm lst) (remove-all atm (remove atm lst)))))

(define (simplify_poly P)
  (cond
    ((not (polynomial? P (cadr (cadr P)))) ("Not a polynomial. Cannot be simplified"))
    (else (remove-all '() (cons '+ (map simplify-term  (cdr P)))))))

(define (simplify-term T )
  (cond
    ((eqv? T 0) '())
    ((and (constant? T) T))
    ((eqv? (get-coefficient T) 0) '())
    ((and (linear_term? T (get-variable T)) (eqv? (get-coefficient T)) 1) (get-variable T))
    ((linear_term? T (get-variable T)) T)
    ((eqv? (get-exponent T) 0) (get-coefficient T))
    ((eqv? (get-exponent T) 1) (list (get-coefficient T) (get-variable T)))
    (else T)))

;============================================================================================
; CSCI 320 Spring 2017
; Symbolic Differentiation Example

; this predicate just tells us whether a term is a constant term
(define constant? number?)

; this predicate tells us whether a term is a variable named x
(define (variable? T x) 
  (and (symbol? T) (eqv? T x)))

; this predicate tells us whether we have a term of the form (k x)
;  representing the polynomial kx
(define (linear_term? T x)
  (cond ((and (number? (car T)) (variable? (cadr T) x) (null? (cddr T))))
        (else #f)))

; this predicate defines a "term in x" as either a constant, the variable x,
;  a linear term in x
;  or a term of the form (a x n), where a and n are numbers.
(define (term? T x) 
  (cond ((constant? T) #t)
        ((variable? T x) #t)
        ((linear_term? T x) #t)
        ((and (number? (car T)) (variable? (cadr T) x) (number? (caddr T))))
        (else #f)))

; this predicate tells us whether a given expression is a list of "terms in x"
(define (term_list? T x)
   (cond ((null? T) #t)
         ((and (term? (car T) x) (term_list? (cdr T) x)) #t)
         (else #f)))


; this predicate defines a polynomial E in x as having the form of a term,
;  or the form (+ term_in_x term_in_x...term_in_x)
;  that is a list whose first element is the plus sign and whose cdr is a list of terms in x
(define (polynomial? T x)
  (cond ((term? T x) #t)
        ((and (eqv? (car T) '+) (term_list? (cdr T) x)) #t)
        (else #f)))

; here we define some more meaningfully named functions for handling terms
; in x of the form (a x n)
(define (get-coefficient T) (car T))
(define (get-variable T) (cadr T))
(define (get-exponent T) (caddr T))


; now we implement symbolic derivatives of terms.

(define (d-term T x)
  (cond ((constant? T) 0)
        ((variable? T x) 1)
        ((linear_term? T x) (get-coefficient T))
        ((term? T x) (list (* (get-coefficient T) (get-exponent T))
                         (get-variable T)
                         (- (get-exponent T) 1)))
        (else 0)))

; the derivative of a list with respect to x of a list of terms will be
;the list of derivatives
(define (d-term_list TL x)
  (cond ((null? TL) '())
        (else (cons(d-term (car TL) x) (d-term_list (cdr TL) x))))) 

; a polynomial is a sum of terms in x, so the derivative of a polynomial is the
;  sum of the derivatives of its constituent terms with respct to x
(define (d-polynomial P x)
  (cond ((term? P x) (d-term P x))
        ((polynomial? P x) (cons (car P)  (d-term_list (cdr P) x)))
        (else 0)))