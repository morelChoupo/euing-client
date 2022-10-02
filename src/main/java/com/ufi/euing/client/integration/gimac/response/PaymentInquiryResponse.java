package com.ufi.euing.client.integration.gimac.response;

public class PaymentInquiryResponse {

    private String intent;
    private String vouchercode;
    private Integer amount;
    private String currency;
    private String state;
    private String description;
    private String issuertrxref;
    private String tomember;
    private String frommember;
    private Long createtime;
    private String acquirertrxref;
    private String accountnumber;
    private String sendermobile;
    private Integer validityduration;
    private String walletdestination;
    private String walletsource;
    private String rejectMessage;

    public PaymentInquiryResponse() {
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getVouchercode() {
        return vouchercode;
    }

    public void setVouchercode(String vouchercode) {
        this.vouchercode = vouchercode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIssuertrxref() {
        return issuertrxref;
    }

    public void setIssuertrxref(String issuertrxref) {
        this.issuertrxref = issuertrxref;
    }

    public String getTomember() {
        return tomember;
    }

    public void setTomember(String tomember) {
        this.tomember = tomember;
    }

    public String getFrommember() {
        return frommember;
    }

    public void setFrommember(String frommember) {
        this.frommember = frommember;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getAcquirertrxref() {
        return acquirertrxref;
    }

    public void setAcquirertrxref(String acquirertrxref) {
        this.acquirertrxref = acquirertrxref;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getSendermobile() {
        return sendermobile;
    }

    public void setSendermobile(String sendermobile) {
        this.sendermobile = sendermobile;
    }

    public Integer getValidityduration() {
        return validityduration;
    }

    public void setValidityduration(Integer validityduration) {
        this.validityduration = validityduration;
    }

    public String getWalletdestination() {
        return walletdestination;
    }

    public void setWalletdestination(String walletdestination) {
        this.walletdestination = walletdestination;
    }

    public String getWalletsource() {
        return walletsource;
    }

    public void setWalletsource(String walletsource) {
        this.walletsource = walletsource;
    }

    public String getRejectMessage() {
        return rejectMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    @Override
    public String toString() {
        return "PaymentInquiryResponse{" + "intent=" + intent + ", vouchercode=" + vouchercode + ", amount=" + amount + ", currency=" + currency + ", state=" + state + ", description=" + description + ", issuertrxref=" + issuertrxref + ", tomember=" + tomember + ", frommember=" + frommember + ", createtime=" + createtime + ", acquirertrxref=" + acquirertrxref + ", accountnumber=" + accountnumber + ", sendermobile=" + sendermobile + ", validityduration=" + validityduration + ", walletdestination=" + walletdestination + ", walletsource=" + walletsource + ", rejectMessage=" + rejectMessage + '}';
    }

}
