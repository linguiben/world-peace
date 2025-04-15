package com.jupiter.excelpio;

public class POReport {
	public String serverName;
	public String dbName;
	public String tName;
	public int xstart;
	public int xend;
	public int ystart;
	public int yend;
	public int sheetIndex;
	public String xlsName;
	public String orderName;
	public String colName;
	public String schema;
	public String template;
	public String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getTName() {
		return tName;
	}

	public void setTName(String name) {
		tName = name;
	}

	public int getXstart() {
		return xstart;
	}

	public void setXstart(int xstart) {
		this.xstart = xstart;
	}

	public int getXend() {
		return xend;
	}

	public void setXend(int xend) {
		this.xend = xend;
	}

	public int getYstart() {
		return ystart;
	}

	public void setYstart(int ystart) {
		this.ystart = ystart;
	}

	public int getYend() {
		return yend;
	}

	public void setYend(int yend) {
		this.yend = yend;
	}

	public String getXlsName() {
		return xlsName;
	}

	public void setXlsName(String xlsName) {
		this.xlsName = xlsName;
	}

	public int getSheetIndex() {
		return this.sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

}
