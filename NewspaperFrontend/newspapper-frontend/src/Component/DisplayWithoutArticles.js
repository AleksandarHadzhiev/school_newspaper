import empty from "../empty.jpg";
import classes from "../css/DisplayWithoutArticles.module.css";

function DisplayWithoutArticles(){
    return (
        <div className={classes.component}>
            <p>No Articles </p>
            <img src={empty}/>
        </div>
    )
}

export default DisplayWithoutArticles;