package com.company.zwh_rfid.uientity;

import java.util.Date;

public class StoragegetID {

        private Date modifiedTime;
        private String modifier;
        private String smAcceptanceType;//验收入库类型(1根据SAP采购订单入库2根据备件通采购订单入库3免费入库)
        private String smStatus;
        private String dr;//删除标记 0.正常 1.删除
        private String smStorekeeperCode;
        private String smAcceptanceFcode;  //验收工厂编码
        private String smAcceptanceTime;
        private String ieopEnterpriseName;
        private String fileUrl;
        private String smCloseTime;
        private String fileGroup;
        private String id;
        private String ieopEnterpriseCode;
        private String creator;
        private String smAccepter;
        private String smProposerCode;
        private String smSapOrderno;
        private Date createTime;
        private String smFlag;
        private String smProposerName;
        private String smIsOrderno;   //入库单编号
        private String enterpriseId;
        private String smAcceptanceFname;
        private Date smProposerTime;
        private String smBjtOrderno;
        private String smStorekeeperName;
        private String ts;

    @Override
    public String toString() {
        return "StoragegetID{" +
                "modifiedTime=" + modifiedTime +
                ", modifier='" + modifier + '\'' +
                ", smAcceptanceType='" + smAcceptanceType + '\'' +
                ", smStatus='" + smStatus + '\'' +
                ", dr='" + dr + '\'' +
                ", smStorekeeperCode='" + smStorekeeperCode + '\'' +
                ", smAcceptanceFcode='" + smAcceptanceFcode + '\'' +
                ", smAcceptanceTime='" + smAcceptanceTime + '\'' +
                ", ieopEnterpriseName='" + ieopEnterpriseName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", smCloseTime='" + smCloseTime + '\'' +
                ", fileGroup='" + fileGroup + '\'' +
                ", id='" + id + '\'' +
                ", ieopEnterpriseCode='" + ieopEnterpriseCode + '\'' +
                ", creator='" + creator + '\'' +
                ", smAccepter='" + smAccepter + '\'' +
                ", smProposerCode='" + smProposerCode + '\'' +
                ", smSapOrderno='" + smSapOrderno + '\'' +
                ", createTime=" + createTime +
                ", smFlag='" + smFlag + '\'' +
                ", smProposerName='" + smProposerName + '\'' +
                ", smIsOrderno='" + smIsOrderno + '\'' +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", smAcceptanceFname='" + smAcceptanceFname + '\'' +
                ", smProposerTime=" + smProposerTime +
                ", smBjtOrderno='" + smBjtOrderno + '\'' +
                ", smStorekeeperName='" + smStorekeeperName + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getSmAcceptanceType() {
        return smAcceptanceType;
    }

    public void setSmAcceptanceType(String smAcceptanceType) {
        this.smAcceptanceType = smAcceptanceType;
    }

    public String getSmStatus() {
        return smStatus;
    }

    public void setSmStatus(String smStatus) {
        this.smStatus = smStatus;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getSmStorekeeperCode() {
        return smStorekeeperCode;
    }

    public void setSmStorekeeperCode(String smStorekeeperCode) {
        this.smStorekeeperCode = smStorekeeperCode;
    }

    public String getSmAcceptanceFcode() {
        return smAcceptanceFcode;
    }

    public void setSmAcceptanceFcode(String smAcceptanceFcode) {
        this.smAcceptanceFcode = smAcceptanceFcode;
    }

    public String getSmAcceptanceTime() {
        return smAcceptanceTime;
    }

    public void setSmAcceptanceTime(String smAcceptanceTime) {
        this.smAcceptanceTime = smAcceptanceTime;
    }

    public String getIeopEnterpriseName() {
        return ieopEnterpriseName;
    }

    public void setIeopEnterpriseName(String ieopEnterpriseName) {
        this.ieopEnterpriseName = ieopEnterpriseName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getSmCloseTime() {
        return smCloseTime;
    }

    public void setSmCloseTime(String smCloseTime) {
        this.smCloseTime = smCloseTime;
    }

    public String getFileGroup() {
        return fileGroup;
    }

    public void setFileGroup(String fileGroup) {
        this.fileGroup = fileGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIeopEnterpriseCode() {
        return ieopEnterpriseCode;
    }

    public void setIeopEnterpriseCode(String ieopEnterpriseCode) {
        this.ieopEnterpriseCode = ieopEnterpriseCode;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSmAccepter() {
        return smAccepter;
    }

    public void setSmAccepter(String smAccepter) {
        this.smAccepter = smAccepter;
    }

    public String getSmProposerCode() {
        return smProposerCode;
    }

    public void setSmProposerCode(String smProposerCode) {
        this.smProposerCode = smProposerCode;
    }

    public String getSmSapOrderno() {
        return smSapOrderno;
    }

    public void setSmSapOrderno(String smSapOrderno) {
        this.smSapOrderno = smSapOrderno;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSmFlag() {
        return smFlag;
    }

    public void setSmFlag(String smFlag) {
        this.smFlag = smFlag;
    }

    public String getSmProposerName() {
        return smProposerName;
    }

    public void setSmProposerName(String smProposerName) {
        this.smProposerName = smProposerName;
    }

    public String getSmIsOrderno() {
        return smIsOrderno;
    }

    public void setSmIsOrderno(String smIsOrderno) {
        this.smIsOrderno = smIsOrderno;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getSmAcceptanceFname() {
        return smAcceptanceFname;
    }

    public void setSmAcceptanceFname(String smAcceptanceFname) {
        this.smAcceptanceFname = smAcceptanceFname;
    }

    public Date getSmProposerTime() {
        return smProposerTime;
    }

    public void setSmProposerTime(Date smProposerTime) {
        this.smProposerTime = smProposerTime;
    }

    public String getSmBjtOrderno() {
        return smBjtOrderno;
    }

    public void setSmBjtOrderno(String smBjtOrderno) {
        this.smBjtOrderno = smBjtOrderno;
    }

    public String getSmStorekeeperName() {
        return smStorekeeperName;
    }

    public void setSmStorekeeperName(String smStorekeeperName) {
        this.smStorekeeperName = smStorekeeperName;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}
