<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:exsl="K:K">  
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>  
<xsl:strip-space elements="*"/>  
<xsl:include href="keys.xslt"/>  
<xsl:function name="exsl:node-set" as="node()">  
  <xsl:param name="n" as="node()"/>  
  <xsl:sequence select="$n"/>  
</xsl:function>  
<xsl:template match="*">  
    <xsl:copy>  
        <xsl:choose>  
            <xsl:when test="name()=exsl:node-set($sourceKeys)/keys/key">*************</xsl:when>  
            <xsl:otherwise>  
                <xsl:apply-templates/>  
            </xsl:otherwise>  
        </xsl:choose>  
    </xsl:copy>  
</xsl:template>  
</xsl:stylesheet>