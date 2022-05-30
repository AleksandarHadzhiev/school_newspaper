import { useState } from "react";
import classes from "../css/PersonalInformation.module.css";
import image from "../image.png";
import EditInformation from "./EditInformation";

function PersonalInformation(props){

    const [isEditing, setIsEditing] = useState(false);
    return (
        <div className={classes.component}>
            <div className={classes.image}>
                <img alt="" src={image} className={classes.img}/>
            </div>
            <div className={classes.content}>
                <div className={classes.info}>
                    <div className={classes.element}>
                        <label>Email:</label>
                        <p>{props.user.email}</p>
                    </div>
                    <div className={classes.element}>
                        <label>Username:</label>
                        <p>{props.user.username}</p>
                    </div>
                    <div  className={classes.element}>
                        <label>Password:</label>
                        <p>*******</p>
                    </div>
                </div>
                <button onClick={()=>{setIsEditing(true)}} className={classes.btn}>Edit</button>
            </div>
            {isEditing !== false ? <EditInformation person={props.user}/> : null}
        </div>
    )
}

export default PersonalInformation;