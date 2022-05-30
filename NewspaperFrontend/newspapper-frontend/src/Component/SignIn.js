import classes from "../css/SignIn.module.css";
import {FaArrowLeft } from 'react-icons/fa';
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import UserChecker from "../serivces/checkers/UserChecker";
import ErrorMessage from "./ErrorMessage";
import axios from "axios";

function SignIn(){

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState(null);

    useEffect(()=>{
        if(errorMessage!=null){
            setInterval(()=>{
                setErrorMessage(null);
            },1800)
        }
    },[errorMessage])

    function checkUser(){
        let message = "";
        message = UserChecker.checkForEmptySpacesSignIn(password,username);
        if(message !== ""){
            return message;
        }
        message = UserChecker.checkForWeakPassowrd(password);
        if(message !== ""){
            return message;
        }

        return message;
    }

    function signIn(){

        const person = {
            username:username,
            password:password
        }

        const url = "http://localhost:8080/login";

        const message = checkUser();

        console.log(person);
        if(message===""){
            axios.post(url, person).then((res)=>{
                if(res.data!==""){
                  localStorage.setItem('token', JSON.stringify(res.data.Authorization));
                  window.location="/";
                }
                setErrorMessage("Not existing user");
              }).catch((err)=>{
                console.error(err);
              })
        }
        else if(message!==""){
            setErrorMessage(message);
        }
      }

    function goBack(){
        window.location="/"
    }

    return(
        <div className={classes.background}>
            <div className={classes.content}>
                <button onClick={goBack} className={classes.goBack}><FaArrowLeft/></button>
                <form className={classes.signInForm}>
                    <div className={classes.inputElements}>
                        <div className={classes.formElement}>
                            <label>Username:</label>
                            <input type="text"  value={username} onChange={(e)=>{setUsername(e.target.value)}} placeholder="Enter username..."/>
                        </div>
                        <div className={classes.formElement}>
                            <label>Password:</label>
                            <input type="password" value={password} onChange={(e)=>{setPassword(e.target.value)}} placeholder="Enter password..."/>
                        </div>
                    </div>
                    <div className={classes.bottom}>
                        <button onClick={(e)=>{e.preventDefault(); signIn();}}>Sign in</button>
                        <p>Do not have an account yet? <Link to="/signUp" className={classes.link}>Sign up</Link></p>
                    </div>
                </form>
            </div>
            {errorMessage !=null ? <ErrorMessage errorMessage={errorMessage}/> : null}
        </div>
    )
}

export default SignIn;