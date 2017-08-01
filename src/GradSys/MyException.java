package GradSys;
//我的异常类
class InputNullException extends Exception {

	InputNullException(String msg){
		super(msg);
	}
}
class ResultSetNullException extends Exception {

	ResultSetNullException(String msg){
		super(msg);
	}
}
class WorkFinshed extends Exception {

	WorkFinshed(String msg){
		super(msg);
	}
}