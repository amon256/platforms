/**
 *  GeneratorAll.java
 */
package platforms.codegenerate;

import java.io.File;

import javax.persistence.Table;

/**
 * 所有重新生成
 * @author fengmengyue
 * @since 2014年7月10日
 */
public class GeneratorAll {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		generate();
		createMainDdl();
	}
	
	private static void generate(){
		File beanDir = new File(System.getProperty("user.dir") + "/src/com/dobandsman/core/beans");
		if(beanDir.exists() && beanDir.isDirectory()){
			File[] beanFiles = beanDir.listFiles();
			for(File beanFile : beanFiles){
				if(beanFile.isFile() && beanFile.getName().endsWith(".java")){
					try{
						Class<?> clazz = Class.forName("com.dobandsman.core.beans." + beanFile.getName().replace(".java", ""));
						Table table = clazz.getAnnotation(Table.class);
						if(table != null){
							CodeGenerator.generate(clazz);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private static void createMainDdl(){
		CodeGenerator.createMainDdl();
	}
}
