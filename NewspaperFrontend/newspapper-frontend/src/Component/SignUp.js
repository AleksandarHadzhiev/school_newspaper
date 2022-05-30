import classes from "../css/SignIn.module.css";
import {FaArrowLeft } from 'react-icons/fa';
import { Link } from "react-router-dom";
import UserServices from "../serivces/UserServices";
import { useEffect, useState } from "react";
import UserChecker from "../serivces/checkers/UserChecker";
import ErrorMessage from "./ErrorMessage";

function SignUp(){

    const [password,setPassword] = useState("");
    const [repeatPassword,setRepeatPassword] = useState("");
    const [email,setEmail] = useState("");
    const [username,setUsername] = useState("");
    const [errorMessage, setErrorMessage] = useState(null);

    useEffect(()=>{
        if(errorMessage!=null){
            setInterval(()=>{
                setErrorMessage(null);
            },1800)
        }
    },[errorMessage])

    const person ={
        "email":email,
        "username":username,
        "password":password
    }

    function checkUser(){
        let message = "";
        message = UserChecker.checkForEmptySpacesSignUp(password,email, repeatPassword,username);
        if(message !==""){
            return message;
        }
        message = UserChecker.checkForWeakPassowrd(password);
        if(message !==""){
            return message;
        }
        message = UserChecker.checkForDifferenceInPasswords(password,repeatPassword);
        if(message !==""){
            return message;
        }
        return message;
    }


    function createAccount(person){
        const message = checkUser();
        if(message === ""){
            UserServices.createAccount(person).then((res)=>{
                if(res.data === "User was created"){
                    window.location="/signIn";
                }
                setErrorMessage(res.data);
            }).catch((err)=>{
                console.error(err);
            })
        }
        else if(message!==""){
            setErrorMessage(message);
        }
    }

    function goBack(){
        window.location="/";
    }

    return(
        <div className={classes.background}>
            <div className={classes.content}>
                <button onClick={goBack} className={classes.goBack}><FaArrowLeft/></button>
                <form className={classes.signInForm}>
                    <div className={classes.inputElements}>
                        <div className={classes.formElement}>
                            <label>Email:</label>
                            <input type="email" value={email} onChange={(e)=>{setEmail(e.target.value)}} placeholder="example@gmail.com"/>
                        </div>
                        <div className={classes.formElement}>
                            <label>Username:</label>
                            <input type="username" value={username} onChange={(e)=>{setUsername(e.target.value)}} placeholder="Enter username..."/>
                        </div>
                        <div className={classes.formElement}>
                            <label>Password:</label>
                            <input type="password" value={password} onChange={(e)=>{setPassword(e.target.value)}} placeholder="Enter password..."/>
                        </div>
                        <div className={classes.formElement}>
                            <label>Repeat Password:</label>
                            <input type="password" value={repeatPassword} onChange={(e)=>{setRepeatPassword(e.target.value)}} placeholder="Enter password..."/>
                        </div>
                    </div>
                    <div className={classes.bottom}>
                        <button onClick={(e)=>{e.preventDefault(); createAccount(person)}}>Sign up</button>
                        <p>Have an account already? <Link to="/signIn" className={classes.link}>Sign in</Link></p>
                    </div>
                </form>
            </div>
            {errorMessage !=null ? <ErrorMessage errorMessage={errorMessage}/> : null}
        </div>
    )
}

export default SignUp;