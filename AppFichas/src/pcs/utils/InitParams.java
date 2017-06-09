package pcs.utils;

public class InitParams {
	
	public static InitParams instance=null;
	
	private static String pathJSP;
	private static String pathCSS;
	private static String pathJS;
	
	private InitParams(){}
	
	private InitParams(String pathJSP, String pathCSS, String pathJS){
		InitParams.pathJSP=pathJSP;
		InitParams.pathCSS=pathCSS;
		InitParams.pathJS=pathJS;
	}
	
	public static InitParams getInstance(String pathJSP, String pathCSS, String pathJS){
		if(instance==null){
			instance=createIntance(pathJSP, pathCSS, pathJS);
		}
		return instance;
	}
	
	private synchronized static InitParams createIntance(String pathJSP, String pathCSS, String pathJS){
		return new InitParams(pathJSP, pathCSS, pathJS);
	}

	public static String getPathJSP() {
		return pathJSP;
	}

	public static String getPathCSS() {
		return pathCSS;
	}

	public static String getPathJS() {
		return pathJS;
	}

	
}
