import './App.css'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faHouse, faMoneyBill, faLock} from "@fortawesome/free-solid-svg-icons";

function App() {

  const swaggerBase = "http://localhost";
  const swaggerRoute = "/swagger-ui/index.html";

  const openSwagger = (servicePath: string) => {
      window.open(swaggerBase + servicePath + swaggerRoute, "_blank");
  }

  return (
    <div>
      <div className="header">
        <img src="../public/assets/logo_white.png" alt="LocaLoge logo" className="logo"/>
      </div>
      <div className="buttons">
        <div className="card" onClick={() => openSwagger("/housing")}>
          <FontAwesomeIcon icon={faHouse} size="2x"/>
          <h2>Housing Service</h2>
          <p>Access the Housing Service Swagger</p>
        </div>
        <div className="card" onClick={() => openSwagger("/rental")}>
          <FontAwesomeIcon icon={faMoneyBill} size="2x"/>
          <h2>Rental Service</h2>
          <p>Access the Rental Service Swagger</p>
        </div>
        <div className="card" onClick={() => openSwagger("/reservation")}>
          <FontAwesomeIcon icon={faLock} size="2x"/>
          <h2>Reservation Service</h2>
          <p>Access the Reservation Service Swagger</p>
        </div>
      </div>
    </div>
  )
}

export default App
