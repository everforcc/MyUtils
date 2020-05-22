package cn.cc.utils.msgtype.json;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author
 * @mail gkl
 * @time 2020-05-13 08:35:44.492
 * 进项发票管理-发票查询页面入参DTO
 */
public class PrpjEcdInvoiceOcrQueryDto {
    private static final long serialVersionUID = 1L;

    //发票代码
    private String invoicecode;

    //发票号码
    private String invoiceno;

    //发票状态
    private String deductionStatus;

    //价税合计金额
    private String taxPayerRegiNumber;

    //开票日期
    private Date inputDateStart;

    //业务类型
    private String businessType;

    //销方名称
    private String sellername;

    //销方识别号
    private String sellerCode;

    //发票类型 1专票 2普票
    private String invoiceType;
    
    //用户
    private UserInfo userDto;
    
    private String fileID;
    
    private List<PrpjEcdInvoiceOcrDto> prpjEdInvoiceOcr;
    
    //抵扣联
    private List<String> deductionform;
    //统一抵扣联    
    private String unifiedDeductionform;
    
    private String invoiceid1;
    
    private String invoiceid2;

	public String getInvoicecode() {
		return invoicecode;
	}

	public void setInvoicecode(String invoicecode) {
		this.invoicecode = invoicecode;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public String getDeductionStatus() {
		return deductionStatus;
	}

	public void setDeductionStatus(String deductionStatus) {
		this.deductionStatus = deductionStatus;
	}

	public String getTaxPayerRegiNumber() {
		return taxPayerRegiNumber;
	}

	public void setTaxPayerRegiNumber(String taxPayerRegiNumber) {
		this.taxPayerRegiNumber = taxPayerRegiNumber;
	}

	public Date getInputDateStart() {
		return inputDateStart;
	}

	public void setInputDateStart(Date inputDateStart) {
		this.inputDateStart = inputDateStart;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public UserInfo getUserDto() {
		return userDto;
	}

	public void setUserDto(UserInfo userDto) {
		this.userDto = userDto;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public List<PrpjEcdInvoiceOcrDto> getPrpjEdInvoiceOcr() {
		return prpjEdInvoiceOcr;
	}

	public void setPrpjEdInvoiceOcr(List<PrpjEcdInvoiceOcrDto> prpjEdInvoiceOcr) {
		this.prpjEdInvoiceOcr = prpjEdInvoiceOcr;
	}

	

	public List<String> getDeductionform() {
		return deductionform;
	}

	public void setDeductionform(List<String> deductionform) {
		this.deductionform = deductionform;
	}

	public String getUnifiedDeductionform() {
		return unifiedDeductionform;
	}

	public void setUnifiedDeductionform(String unifiedDeductionform) {
		this.unifiedDeductionform = unifiedDeductionform;
	}

	public String getInvoiceid1() {
		return invoiceid1;
	}

	public void setInvoiceid1(String invoiceid1) {
		this.invoiceid1 = invoiceid1;
	}

	public String getInvoiceid2() {
		return invoiceid2;
	}

	public void setInvoiceid2(String invoiceid2) {
		this.invoiceid2 = invoiceid2;
	}

	
	
}
