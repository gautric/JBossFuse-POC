<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xalan="http://xml.apache.org/xalan"
	xmlns:cje="com.redhat.poc.xslt.BuiltinExecution"
	extension-element-prefixes="cje xalan">
	<xsl:output omit-xml-declaration="no" indent="yes" />
	<xalan:component prefix="cje" functions="toto"
		elements="element">
		<xalan:script lang="javaclass"
			src="xalan://com.redhat.poc.xslt.BuiltinExecution" />
	</xalan:component>

	<xsl:param name="geoDecoding" />

	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>
	<xsl:template match="geo">
		<geo>
			<xsl:value-of select="cje:internal($geoDecoding)" />
		</geo>
	</xsl:template>

</xsl:stylesheet>