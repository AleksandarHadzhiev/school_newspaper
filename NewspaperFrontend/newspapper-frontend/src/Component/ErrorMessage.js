import classes from "../css/ErrorMessage.module.css";

function ErrorMessage(props){

    return (
        <div className={classes.background}>
            <div className={classes.content}>
                <p>{props.errorMessage}</p>
            </div>
        </div>
    )
}

export default ErrorMessage;