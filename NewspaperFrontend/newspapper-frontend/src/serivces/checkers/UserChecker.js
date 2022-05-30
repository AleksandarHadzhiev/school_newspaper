class UserChecker{
    checkForEmptySpacesSignIn(password,email) {
        if(password.trim() === ""){
            return "Missing Password";
        }
        else if(email.trim() === ""){
            return "Missing Email";
        }

        return "";
    }

    checkForEmptySpacesEdit(password,email,username) {
        if(password.trim() === ""){
            return "Missing Password";
        }
        else if(email.trim() === ""){
            return "Missing Email";
        }
        else if(username.trim() === ""){
            return "Missing Email";
        }
        return "";
    }

    checkForEmptySpacesSignUp(password,email, repeatPassword,username) {
        if(password.trim() === ""){
            return "Missing Password";
        }
        else if(email.trim() === ""){
            return "Missing Email";
        }
        else if(repeatPassword.trim() === ""){
            return "Missing Repeat Password";
        }
        else if(username.trim() === ""){
            return "Missing Repeat Password";
        }
        return "";
    }

    checkForWeakPassowrd(password){
        if(password.length<6){
            return "Weak Password";
        }

        return "";
    }

    checkForDifferenceInPasswords(password,repeatPassword){
        if(password!==repeatPassword){
            return "Different Passwords";
        }

        return "";
    }
}
export default new UserChecker();