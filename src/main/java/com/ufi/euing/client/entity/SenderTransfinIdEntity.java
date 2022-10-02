package com.ufi.euing.client.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class SenderTransfinIdEntity  implements Serializable {

    private String codeTypePiece;

    private String id1Number;

    private String dateOfIssue;

    private String dateOfExpiry;

    private String countryOfIssue;

    private String dateOfBirth;

    private String placeOfBirth;

    private String occupation;

    private String gender;

    private String stateOfIssue;

    private String nationality;

    public SenderTransfinIdEntity() {
    }

    public SenderTransfinIdEntity(
            String codeTypePiece,
            String ID1Number,
            String dateOfIssue,
            String dateOfExpiry,
            String countryOfIssue,
            String dateOfBirth,
            String placeOfBirth,
            String occupation,
            String socialSecurityNo,
            String gender,
            String stateOfIssue,
            String nationality) {
        this.codeTypePiece = codeTypePiece;
        this.id1Number = ID1Number;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
        this.countryOfIssue = countryOfIssue;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.occupation = occupation;
        this.gender = gender;
        this.stateOfIssue = stateOfIssue;
        this.nationality = nationality;
    }


    @Override
    public String toString() {
        return "SenderIdEntity [codeTypePiece=" + codeTypePiece + ", ID1Number="
                + id1Number + ", dateOfIssue=" + dateOfIssue
                + ", dateOfExpiry=" + dateOfExpiry + ", countryOfIssue="
                + countryOfIssue + ", dateOfBirth=" + dateOfBirth
                + ", placeOfBirth=" + placeOfBirth + ", occupation="
                + occupation + ", gender=" + gender + ", stateOfIssue="
                + stateOfIssue + ", nationality=" + nationality + "]";
    }


}
