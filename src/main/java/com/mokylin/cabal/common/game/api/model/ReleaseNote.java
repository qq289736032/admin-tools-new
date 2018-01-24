package com.mokylin.cabal.common.game.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/4
 * admin-tools
 */
public class ReleaseNote implements Serializable {

    private static final long serialVersionUID = 5763996282585716725L;

    private List<String> serverIdList;

    private String versionId;   //版本号

    private String releaseTime; //版本时间

    private String releaseNote; //更新说明

    private String releaseContent; //更新内容

    public List<String> getServerIdList() {
        return serverIdList;
    }

    public void setServerIdList(List<String> serverIdList) {
        this.serverIdList = serverIdList;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getReleaseNote() {
        return releaseNote;
    }

    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }

    public String getReleaseContent() {
        return releaseContent;
    }

    public void setReleaseContent(String releaseContent) {
        this.releaseContent = releaseContent;
    }

    @Override
    public String toString() {
        return "ReleaseNote{" +
                "serverIdList=" + serverIdList +
                ", versionId='" + versionId + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", releaseNote='" + releaseNote + '\'' +
                ", releaseContent='" + releaseContent + '\'' +
                '}';
    }
}
