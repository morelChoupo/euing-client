package com.ufi.euing.client.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Data
@Getter
@Setter
public class ReceiverTransfinIdEntity implements Serializable {

    private String codeTypePieceRe;

    private String rid1Number;

    private String dateOfIssue;

    private String dateOfExpiry;

    private String countryOfIssue;

    private String resident;

    private String occupation;

    private String gender;

    private String stateOfIssue;

    private String nationality;

    public ReceiverTransfinIdEntity() {
    }

    public ReceiverTransfinIdEntity(String codeTypePieceRe, String rID1Number,
                                    String dateOfIssue, String dateOfExpiry, String countryOfIssue,
                                    String resident, String occupation, String gender,
                                    String stateOfIssue, String nationality) {
        super();
        this.codeTypePieceRe = codeTypePieceRe;
        rid1Number = rID1Number;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
        this.countryOfIssue = countryOfIssue;
        this.resident = resident;
        this.occupation = occupation;
        this.gender = gender;
        this.stateOfIssue = stateOfIssue;
        this.nationality = nationality;
    }


    @Override
    public String toString() {
        return "ReceiverTransfinIdEntity [codeTypePieceRe=" + codeTypePieceRe
                + ", RID1Number=" + rid1Number + ", dateOfIssue=" + dateOfIssue
                + ", dateOfExpiry=" + dateOfExpiry + ", countryOfIssue="
                + countryOfIssue + ", resident=" + resident + ", occupation="
                + occupation + ", gender=" + gender + ", stateOfIssue="
                + stateOfIssue + ", nationality=" + nationality + "]";
    }

}
