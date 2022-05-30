import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import classes from "../css/NavigationBar.module.css";
import {FaUser } from 'react-icons/fa';
import jwt_decode from "jwt-decode";
import UserServices from "../serivces/UserServices";

function NavigationBar(){

    const [token, setToken] = useState(null);
    const [username, setUsername] = useState(null);

    useEffect(()=>{
        if(JSON.parse(localStorage.getItem('token'))!=null){
            setToken(JSON.parse(localStorage.getItem('token')));
            if(token!=null){
                
            setUsername(jwt_decode(token));
            }
        }
    },[token])

    useEffect(()=>{
        if(username!=null){
            UserServices.findByUsername(username.sub, token).then((res)=>{
                localStorage.setItem('user', JSON.stringify(res.data));
            })
            .catch((err)=>{
                console.log(err);
            })   
        }  
    },[username])

    return(
        <div className={classes.navigationBar}>
            <div className={classes.content}>
                <p className={classes.siteName}>School Newspaper</p>
                <div className={classes.links}>
                    <Link to="/" className={classes.link}>Home</Link>
                    {token !== null ? <Link to="/profile" className={classes.link}><FaUser/>  Profile</Link> : <Link to="/signIn" className={classes.link}>Sign in</Link>}
                </div>
            </div>
        </div>
    )

}

export default NavigationBar;