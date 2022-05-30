import './App.css';
import SignIn from './Component/SignIn';
import SignUp from './Component/SignUp';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './Pages/Home';
import Profile from './Pages/Profile';

function App() {



  return (
    <div className="App">
      <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/signIn" element={<SignIn/>}/>
            <Route path="/signUp" element={<SignUp/>}/>
            <Route path="/profile" element={<Profile/>}/>
          </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
