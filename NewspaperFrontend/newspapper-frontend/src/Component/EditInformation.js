import classes from "../css/SignIn.module.css";
import {FaArrowLeft } from 'react-icons/fa';
import UserServices from "../serivces/UserServices";
import { useEffect, useState } from "react";
import UserChecker from "../serivces/checkers/UserChecker";
import ErrorMessage from "./ErrorMessage";


function EditInformation(props){
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage,setErrorMessage] = useState(null);

    const person ={
        "id":props.person.id,
        "email":email,
        "username":username,
        "password":password
    }

    const [token, setToken] = useState(null);

    useEffect(()=>{
        if(JSON.parse(localStorage.getItem('token'))!=null){
            setToken(JSON.parse(localStorage.getItem('token')));
        }
    },[])

    useEffect(()=>{
        if(errorMessage!=null){
            setInterval(()=>{
                setErrorMessage(null);
            },1800)
        }
    },[errorMessage])

    function checkUser(){
        let message = "";
        message = UserChecker.checkForEmptySpacesSignIn(password,email,username);
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

        const message = checkUser();

        if(message===""){
            UserServices.editInformation(person,token).then((res)=>{
                if(res.data==="Updated user information"){
                    getUser();
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
       

    function getUser(){
        UserServices.findByUsername(email,token).then((res)=>{
            if(res.data !== ""){
              localStorage.setItem('user', JSON.stringify(res.data));
              window.location="/profile";
            }
          }).catch((err)=>{
            console.error(err);
          })
    }

    function goBack(){
        window.location="/profile"
    }

    return(
        <div className={classes.background}>
            <div className={classes.content}>
                <button onClick={goBack} className={classes.goBack}><FaArrowLeft/></button>
                <form className={classes.signInForm}>
                    <div className={classes.inputElements}>
                        <div className={classes.formElement}>
                            <label>Email:</label>
                            <input type="email"  value={email} onChange={(e)=>{setEmail(e.target.value)}} placeholder="example@gmail.com"/>
                        </div>
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
                        <button onClick={(e)=>{e.preventDefault(); signIn();}}>Edit</button>
                        <p>Edit your information</p>
                    </div>
                </form>
            </div>
            {errorMessage !== null ? <ErrorMessage errorMessage={errorMessage}/> : null}
        </div>
    )
}

export default EditInformation;