package platforms.codegenerate;

public class DbField{
	/**
	 * 属性名
	 */
	private String fieldName;
	/**
	 * 列名
	 */
	private String columnName;
	/**
	 * jdbcType
	 */
	private String jdbcType;
	/**
	 * 数据库类型
	 */
	private String dbType;
	
	/**
	 * 长度
	 */
	private int length;
	
	/**
	 * 是否连接字段
	 */
	private boolean joinField = false;
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public boolean getJoinField() {
		return joinField;
	}
	public void setJoinField(boolean joinField) {
		this.joinField = joinField;
	}
}