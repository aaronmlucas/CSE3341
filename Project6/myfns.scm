; call to start the interpreter
(define (plan program)
	???
)

(define (evalPlanProg expr)
	???
)

#|
Evaluates the expression based on its type. Calls one of the functions below.
|#
(define (evalPlanExpr expr)
	???
)

#|
cond is evaluated:
	- if it is >0, thenExpr is evaluated
	- if it is <=0, elseExpr is evaluated
|#
(define (evalPlanIf cond thenExpr elseExpr)
	???
)

#|
evaluate and return x + y
|#
(define (evalPlanAdd x y)
	(+ 
		(evalPlanExpr '(x)) 
		(evalPlanExpr '(y)) )
)

#| 
evaluate and return x * y
|#
(define (evalPlanMul x y)
	(* 
		(evalPlanExpr '(x)) 
		(evalPlanExpr '(y)) )
)

#|
evaluate and return x - y
|#
(define (evalPlanSub x y)
	(- 
		(evalPlanExpr '(x)) 
		(evalPlanExpr '(y)) )
)

#|
varId: identifier/variable name.
valExpr: value to be assigned to varId. Note - this binding is only active locally.
bodyExpr: expression to be evaluated. Integer value is returned.
|#
(define (evalPlanLet varId valExpr bodyExpr)
	???
)

