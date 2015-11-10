/**
 *  JoinField.java
 */
package platforms.codegenerate;

import java.util.List;

/**
 * @author fengmengyue
 * @since 2014年7月3日
 */
public class JoinField {

	private String tableName;
	
	private String tableAlias;
	
	private String joinColumn;
	
	private List<DbField> tableFields;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	public String getJoinColumn() {
		return joinColumn;
	}

	public void setJoinColumn(String joinColumn) {
		this.joinColumn = joinColumn;
	}

	public List<DbField> getTableFields() {
		return tableFields;
	}

	public void setTableFields(List<DbField> tableFields) {
		this.tableFields = tableFields;
	}
}
