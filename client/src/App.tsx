import './App.css'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faHouse, faMoneyBill, faLock} from "@fortawesome/free-solid-svg-icons";
import React, {useState} from "react";

function App() {

  const [cloudChecked, setCloudChecked] = useState(false);
  const swaggerBase = "http://localhost";
  const swaggerRoute = "/swagger-ui/index.html";

  const handleCheck = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCloudChecked(event.target.checked);
  }

  const openSwagger = (servicePath: string, localPort: string) => {
    if (!cloudChecked) {
      window.open(swaggerBase + ":" + localPort + swaggerRoute, "_blank");
    } else {
      window.open(swaggerBase + servicePath + swaggerRoute, "_blank");
    }
  }

  return (
    <div>
      <div className="header">
        <img src="../public/assets/logo_white.png" alt="LocaLoge logo" className="logo"/>
      </div>
      <div className="selector">
        <h3 className={cloudChecked ? "" : "h3-checked"}>
          Local
        </h3>
        <label className="switch">
          <input type="checkbox" onChange={handleCheck}/>
          <span className="slider round"></span>
        </label>
        <h3 className={cloudChecked ? "h3-checked" : ""}>
          Cloud
        </h3>
      </div>
      <div className="buttons">
        <div className="card" onClick={() => openSwagger("/housing", "8080")}>
          <FontAwesomeIcon icon={faHouse} size="2x"/>
          <h2>Housing Service</h2>
          <p>Access the Housing Service Swagger</p>
        </div>
        <div className="card" onClick={() => openSwagger("rental", "8081")}>
          <FontAwesomeIcon icon={faMoneyBill} size="2x"/>
          <h2>Rental Service</h2>
          <p>Access the Rental Service Swagger</p>
        </div>
        <div className="card" onClick={() => openSwagger("/reservation", "8082")}>
          <FontAwesomeIcon icon={faLock} size="2x"/>
          <h2>Reservation Service</h2>
          <p>Access the Reservation Service Swagger</p>
        </div>
      </div>
    </div>
  )
}

export default App
