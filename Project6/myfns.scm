;;; Aaron Lucas
;;; 07/18/2024
;;; CSE 3341 Project 6

;; call to start the interpreter
(define (plan program)
	(evalPlanExpr '() (cadr program))
)

;; evaluates the expression based on its type. Calls one of the functions below.
(define (evalPlanExpr bindings expr)
	(if (list? expr)
		(cond
			((equal? (car expr) 'planAdd) (evalPlanAdd bindings (cadr expr) (caddr expr))) ; addition
			((equal? (car expr) 'planMul) (evalPlanMul bindings (cadr expr) (caddr expr))) ; multiplication
			((equal? (car expr) 'planSub) (evalPlanSub bindings (cadr expr) (caddr expr))) ; subtraction
			((equal? (car expr) 'planIf) (evalPlanIf bindings (cadr expr) (caddr expr) (cadddr expr))) ; conditional
			((equal? (car expr) 'planLet) (evalPlanLet bindings (cadr expr) (caddr expr) (cadddr expr))) ; let		
		)
		(if (integer? expr) 
			expr ; constant
			(evalPlanId bindings expr) ; variable id
		)
	)
)

(define (evalPlanId bindings id)
	; returns the value of the variable
	; if the variable is not found, print an error message.
	(if (null? bindings) ((display "Variable not found.")(newline))
		(if (equal? (caar bindings) id) 
			(cdar bindings)
			; else continue search recursively
			(evalPlanId (cdr bindings) id)
		)
	)
)

;;cond is evaluated:
;;	- if it is >0, thenExpr is evaluated
;;	- if it is <=0, elseExpr is evaluated
(define (evalPlanIf bindings cond thenExpr elseExpr)
	(if (> (evalPlanExpr bindings cond) 0)
		(evalPlanExpr bindings thenExpr)
		(evalPlanExpr bindings elseExpr)
	)
)


;;evaluate and return x + y
(define (evalPlanAdd bindings x y)
	(+ (evalPlanExpr bindings x) (evalPlanExpr bindings y))
)

;;evaluate and return x * y
(define (evalPlanMul bindings x y)
	(* (evalPlanExpr bindings x) (evalPlanExpr bindings y))
)

;;evaluate and return x - y
(define (evalPlanSub bindings x y)
	(- (evalPlanExpr bindings x) (evalPlanExpr bindings y))
)


;;varId: identifier/variable name.
;;valExpr: value to be assigned to varId. Note - this binding is only active in the bodyExpr.
;;bodyExpr: expression to be evaluated. Integer value is returned.
(define (evalPlanLet bindings varId valExpr bodyExpr)
	; appends (varId . valExpr) to the beginning of the bindings list
	; this results in a bindings list in the form: (...(c . 3) (b . 2) a . 1 )
	(evalPlanExpr (cons (cons varId (evalPlanExpr bindings valExpr)) bindings) bodyExpr)
)

