package cn.cc.utils.msgtype.json;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PrpjEcdInvoiceOcr  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long idEcdinvoiceocr;

    private String correctcode;

    private String invoicecode;

    private String invoiceno;

    private String billingdate;

    private String buyeridentifnumber;

    private String buyername;

    private String buyeradress;

    private String buyeraccount;

    private String taxrate;

    private String sellername;

    private String selleridentifinumber;

    private String selleradress;

    private String selleraccount;
    
    private String sellerBankAccount;//由于改为手动录入的方式新增开户账号字段
    
    private String sellerPhone;//由于改为手动录入的方式新增销方电话字段
    
    private Date endDate;//抵扣有效期

    private String sumall;

    private String sumfeebig;

    private String sumfee;

    private String sumtaxfee;

    private String goodslist;

    private String pricelist;

    private String taxratelist;

    private String taxfeelist;

    private String unitlist;

    private String quantitylist;

    private String unitpricelist;

    private String goodstypelist;

    private String invoicetype;

    /**登记页面勾选的发票类型    临时变量 */
	private String invoicetypeSel;
    
    private String bigtype;

    private String errorcode;

    private String errormessage;

    private String remark;

    private String statusflag;

    private String operatorcode;

    private String machineno;

    private String picpath;

    private String picpathDuplicate;

    private Date operatordate;
    
    private String businessType;//业务类型
        
	/**图片对应的文件流对应的字符串    临时变量 */
	private String imageStreamStr;
	
	/**副联图片名称图片的名称    临时变量*/
	private String imageNameDup;
	
	/**副联图片对应的文件流对应的字符串      临时变量 */
	private String imageStreamStrDup;

	/**图片的名称   临时变量*/
	private String imageName;
	
	/**
	 * 是不是补传的发票   1表示是
	 */
	private String isReSendInvoice;
	/**
	 * 发票登记人中文名称
	 */
	private String userName;
	
    private String oldinvoicecode;

	private String oldinvoiceno;
	
	private String freetaxfee;
	
	private String statuscode;
	
	private String flag;
	
	/**
	 * 上传抵扣联标志，默认为空，为1表示有传，
	 */
	private String picpathFlag;
	
	private List<PrpjEcdInvoiceOcr> prpjecdInvoiceOcrList;

	public String getOldinvoicecode() {
		return oldinvoicecode;
	}

	public void setOldinvoicecode(String oldinvoicecode) {
		this.oldinvoicecode = oldinvoicecode;
	}

	public String getOldinvoiceno() {
		return oldinvoiceno;
	}

	public void setOldinvoiceno(String oldinvoiceno) {
		this.oldinvoiceno = oldinvoiceno;
	}

	public String getFreetaxfee() {
		return freetaxfee;
	}

	public void setFreetaxfee(String freetaxfee) {
		this.freetaxfee = freetaxfee;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getIdEcdinvoiceocr() {
        return idEcdinvoiceocr;
    }

    public void setIdEcdinvoiceocr(Long idEcdinvoiceocr) {
        this.idEcdinvoiceocr = idEcdinvoiceocr;
    }

    
    public String getIsReSendInvoice() {
		return isReSendInvoice;
	}

	public void setIsReSendInvoice(String isReSendInvoice) {
		this.isReSendInvoice = isReSendInvoice;
	}

	public String getCorrectcode() {
        return correctcode;
    }

    public void setCorrectcode(String correctcode) {
        this.correctcode = correctcode == null ? null : correctcode.trim();
    }

    public String getInvoicecode() {
        return invoicecode;
    }

    public void setInvoicecode(String invoicecode) {
        this.invoicecode = invoicecode == null ? null : invoicecode.trim();
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno == null ? null : invoiceno.trim();
    }

    public String getBillingdate() {
        return billingdate;
    }

    public void setBillingdate(String billingdate) {
        this.billingdate = billingdate == null ? null : billingdate.trim();
    }

    public String getBuyeridentifnumber() {
        return buyeridentifnumber;
    }

    public void setBuyeridentifnumber(String buyeridentifnumber) {
        this.buyeridentifnumber = buyeridentifnumber == null ? null : buyeridentifnumber.trim();
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername == null ? null : buyername.trim();
    }

    public String getBuyeradress() {
        return buyeradress;
    }

    public void setBuyeradress(String buyeradress) {
        this.buyeradress = buyeradress == null ? null : buyeradress.trim();
    }

    public String getBuyeraccount() {
        return buyeraccount;
    }

    public void setBuyeraccount(String buyeraccount) {
        this.buyeraccount = buyeraccount == null ? null : buyeraccount.trim();
    }

    public String getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(String taxrate) {
        this.taxrate = taxrate == null ? null : taxrate.trim();
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public String getSelleridentifinumber() {
        return selleridentifinumber;
    }

    public void setSelleridentifinumber(String selleridentifinumber) {
        this.selleridentifinumber = selleridentifinumber == null ? null : selleridentifinumber.trim();
    }

    public String getSelleradress() {
        return selleradress;
    }

    public void setSelleradress(String selleradress) {
        this.selleradress = selleradress == null ? null : selleradress.trim();
    }

    public String getSelleraccount() {
        return selleraccount;
    }

    public void setSelleraccount(String selleraccount) {
        this.selleraccount = selleraccount == null ? null : selleraccount.trim();
    }

    public String getSumall() {
        return sumall;
    }

    public void setSumall(String sumall) {
        this.sumall = sumall == null ? null : sumall.trim();
    }

    public String getSumfeebig() {
        return sumfeebig;
    }

    public void setSumfeebig(String sumfeebig) {
        this.sumfeebig = sumfeebig == null ? null : sumfeebig.trim();
    }

    public String getSumfee() {
        return sumfee;
    }

    public void setSumfee(String sumfee) {
        this.sumfee = sumfee == null ? null : sumfee.trim();
    }

    public String getSumtaxfee() {
        return sumtaxfee;
    }

    public void setSumtaxfee(String sumtaxfee) {
        this.sumtaxfee = sumtaxfee == null ? null : sumtaxfee.trim();
    }

    public String getGoodslist() {
        return goodslist;
    }

    public void setGoodslist(String goodslist) {
        this.goodslist = goodslist == null ? null : goodslist.trim();
    }

    public String getPricelist() {
        return pricelist;
    }

    public void setPricelist(String pricelist) {
        this.pricelist = pricelist == null ? null : pricelist.trim();
    }

    public String getTaxratelist() {
        return taxratelist;
    }

    public void setTaxratelist(String taxratelist) {
        this.taxratelist = taxratelist == null ? null : taxratelist.trim();
    }

    public String getTaxfeelist() {
        return taxfeelist;
    }

    public void setTaxfeelist(String taxfeelist) {
        this.taxfeelist = taxfeelist == null ? null : taxfeelist.trim();
    }

    public String getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(String unitlist) {
        this.unitlist = unitlist == null ? null : unitlist.trim();
    }

    public String getQuantitylist() {
        return quantitylist;
    }

    public void setQuantitylist(String quantitylist) {
        this.quantitylist = quantitylist == null ? null : quantitylist.trim();
    }

    public String getUnitpricelist() {
        return unitpricelist;
    }

    public void setUnitpricelist(String unitpricelist) {
        this.unitpricelist = unitpricelist == null ? null : unitpricelist.trim();
    }

    public String getGoodstypelist() {
        return goodstypelist;
    }

    public void setGoodstypelist(String goodstypelist) {
        this.goodstypelist = goodstypelist == null ? null : goodstypelist.trim();
    }

    public String getInvoicetype() {
        return invoicetype;
    }

    public void setInvoicetype(String invoicetype) {
        this.invoicetype = invoicetype == null ? null : invoicetype.trim();
    }
    
    public String getInvoicetypeSel() {
		return invoicetypeSel;
	}

	public void setInvoicetypeSel(String invoicetypeSel) {
		this.invoicetypeSel = invoicetypeSel== null ? "" :invoicetypeSel.trim();
	}

	public String getBigtype() {
        return bigtype;
    }

    public void setBigtype(String bigtype) {
        this.bigtype = bigtype == null ? null : bigtype.trim();
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode == null ? null : errorcode.trim();
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage == null ? null : errormessage.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatusflag() {
        return statusflag;
    }

    public void setStatusflag(String statusflag) {
        this.statusflag = statusflag == null ? null : statusflag.trim();
    }

    public String getOperatorcode() {
        return operatorcode;
    }

    public void setOperatorcode(String operatorcode) {
        this.operatorcode = operatorcode == null ? null : operatorcode.trim();
    }

    public String getMachineno() {
        return machineno;
    }

    public void setMachineno(String machineno) {
        this.machineno = machineno == null ? null : machineno.trim();
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath == null ? null : picpath.trim();
    }

    public String getPicpathDuplicate() {
        return picpathDuplicate;
    }

    public void setPicpathDuplicate(String picpathDuplicate) {
        this.picpathDuplicate = picpathDuplicate == null ? null : picpathDuplicate.trim();
    }

    public Date getOperatordate() {
        return operatordate;
    }

    public void setOperatordate(Date operatordate) {
        this.operatordate = operatordate;
    }

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getImageStreamStr() {
		return imageStreamStr;
	}

	public void setImageStreamStr(String imageStreamStr) {
		this.imageStreamStr = imageStreamStr;
	}

	public String getImageNameDup() {
		return imageNameDup;
	}

	public void setImageNameDup(String imageNameDup) {
		this.imageNameDup = imageNameDup;
	}

	public String getImageStreamStrDup() {
		return imageStreamStrDup;
	}

	public void setImageStreamStrDup(String imageStreamStrDup) {
		this.imageStreamStrDup = imageStreamStrDup;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getSellerBankAccount() {
		return sellerBankAccount;
	}

	public void setSellerBankAccount(String sellerBankAccount) {
		this.sellerBankAccount = sellerBankAccount;
	}

	public String getSellerPhone() {
		return sellerPhone;
	}

	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<PrpjEcdInvoiceOcr> getPrpjecdInvoiceOcrList() {
		return prpjecdInvoiceOcrList;
	}

	public void setPrpjecdInvoiceOcrList(
			List<PrpjEcdInvoiceOcr> prpjecdInvoiceOcrList) {
		this.prpjecdInvoiceOcrList = prpjecdInvoiceOcrList;
	}

	public String getPicpathFlag() {
		return picpathFlag;
	}

	public void setPicpathFlag(String picpathFlag) {
		this.picpathFlag = picpathFlag == null ? "" : picpathFlag.trim();
	}
    
}