package GradSys;
//�ҵ��쳣��
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