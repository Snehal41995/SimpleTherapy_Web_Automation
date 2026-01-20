package com.simpleTherapy.web.pages;

import com.simpleTherapy.web.utils.ExcelReader;

public class CustomerConfiguration extends BaseClass {

    // Declare ExcelReader instance to read data from Excel file
    private final ExcelReader reader;

    // Declare multiple sheet name variables for different test modules
    private final String sheetName1;

    private final int rowNumber;

    // Constructor initializes ExcelReader and assigns sheet names
    public CustomerConfiguration(String sheetName, int rowNumber) {
        this.reader = new ExcelReader(Constants.SimpleTherapy_TestData);
        this.sheetName1 = sheetName;
        this.rowNumber = rowNumber;
    }

    // ---------- Landing Page Data ----------
    // Fetch title heading
    public String getTitleHeadingFromExcel() {
        return reader.getCellData(sheetName1, "TitleHeading", 2);
    }

    // Fetch employer name
    public String getEmployerNameFromExcel () {
        return reader.getCellData(sheetName1, "EmployerName", 2);
    }

    // Fetch operator heading
    public String getChatBotHeadingFromExcel() {
        return reader.getCellData(sheetName1, "ChatBotHeading", 2);
    }

    // Fetch popup heading
    public  String getPopupHeadingFromExcel() {
        return reader.getCellData(sheetName1, "NeedHelpPopupHeading", 2);
    }

    // ---------- Consent Page Data ----------
    public String getConsentHeadingFromExcel() {
        return reader.getCellData(sheetName1, "ConsentHeading", 2).trim();
    }

    // ---------- Great News Page Data ----------
    public String getGreatNewsHeadingFromExcel() {
        return reader.getCellData(sheetName1, "GreatNewsHeading", 2);
    }

    // ---------- Name Details Page Data ----------
    public String getNameDetailsHeadingFromExcel() {
        return reader.getCellData(sheetName1, "NameDetailsHeading", 2);
    }

    public String getFirstNameFromExcel() {
        return reader.getCellData(sheetName1, "FirstName", rowNumber);
    }

    public String getLastNameFromExcel() {
        return reader.getCellData(sheetName1, "LastName", rowNumber);
    }

    public String getPrefNameFromExcel() {
        return reader.getCellData(sheetName1, "PrefName",2);
    }

    // ---------- First Greeting Page Data ----------
    public String getFirstGreetingHeadingFromExcel() {
        return reader.getCellData(sheetName1, "FirstGreetingHeading", 2);
    }

    // ---------- Second Greeting Page Data ----------
    public String getSecondGreetingHeadingFromExcel() {
        return reader.getCellData(sheetName1, "SecondGreetingHeading", 2);
    }

    // ---------- Third Greeting Page Data ----------
    public String getThirdGreetingHeadingFromExcel() {
        return reader.getCellData(sheetName1, "ThirdGreetingHeading", 2);
    }

    // ---------- Personal Information Page Data ----------
    public String getPersonalInfoHeadingFromExcel() {
        return reader.getCellData(sheetName1, "PersonalInfoHeading", 2);
    }

    public String getPhoneNumberFromExcel() {
        return reader.getCellData(sheetName1, "PhoneNumber", 2);
    }

    // ---------- Address Details Page Data ----------
    public String getAddressDetailsHeadingFromExcel() {
        return reader.getCellData(sheetName1, "AddressHeading", 2);
    }

    public String getAddress1FromExcel() {
        return reader.getCellData(sheetName1, "Address1", 2);
    }

    // ---------- Activation Code Page Data ----------
    public String getActivationCdeHeadingFromExcel() {
        return reader.getCellData(sheetName1, "ActivationCodeHeading", 2);
    }

    public String getActivationCodeFromExcel() {
        return reader.getCellData(sheetName1, "ActivationCode", 2);
    }

    // ---------- SignUp Password Page Data ----------
    public String getSignUpPasswordHeadingFromExcel() {
        return reader.getCellData(sheetName1, "SignUpPasswordHeading", 2);
    }

    public String getSignUpPasswordFromExcel() {
        return reader.getCellData(sheetName1, "Password", 2);
    }

    // ---------- Account Security Page Data ----------
    public String getAccountSecurityHeadingFromExcel() {
        return reader.getCellData(sheetName1, "AccountSecurityHeading", 2);
    }

    // ---------- Account Security Otp Page Data ----------
    public String getAccountSecurityOtpHeadingFromExcel() {
        return reader.getCellData(sheetName1, "AccountSecurityOtpHeading", 2);
    }

    // ---------- MFA ----------
    public String getAuthenticatorSecretFromExcel() {
        return reader.getCellData(sheetName1, "AuthSecret", 2).trim();
    }

    // ---------- Post Auth Page Data ----------
    public String getPostAuthHeadingFromExcel() {
        return reader.getCellData(sheetName1, "PostAuthHeading", 2);
    }

    // ---------- Benefit Card Page Data ----------
    public String getBenefitCardHeadingFromExcel() {
        return reader.getCellData(sheetName1, "BenefitCardHeading", 2);
    }

    // ---------- Display Name Logic ----------
    public String getExpectedDisplayName() {
        String prefName = reader.getCellData(sheetName1, "PrefName", rowNumber);
        String firstName = reader.getCellData(sheetName1, "FirstName", rowNumber);

        // If preferred name is empty or null → use first name
        if (prefName == null || prefName.trim().isEmpty()) {
            return firstName.trim();
        }

        // Else use preferred name
        return prefName.trim();
    }

    // ---------- Profile Page Data ----------
    public String getProfileDetailsHeadingFromExcel () {
        return reader.getCellData(sheetName1, "ProfileDetailsHeading", 2);
    }

    public String getSelectedHeightFromExcel() {
        return reader.getCellData(sheetName1, "updHeight", 2);
    }

    public String getSelectedWeightFromExcel() {
        return reader.getCellData(sheetName1, "updWeight", 2);
    }


}


