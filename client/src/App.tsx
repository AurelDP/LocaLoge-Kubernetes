import './App.css'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faHouse, faMoneyBill, faLock} from "@fortawesome/free-solid-svg-icons";

function App() {
  return (
    <div>
      <div className="header">
        <img src="../public/assets/logo_white.png" alt="LocaLoge logo" className="logo"/>
      </div>
      <div className="buttons">
        <div className="card" onClick={() => window.open("http://localhost:8081/swagger-ui/", "_blank")}>
          <FontAwesomeIcon icon={faHouse} size="2x"/>
          <h2>Housing Service</h2>
          <p>Access the Housing Service Swagger</p>
        </div>
        <div className="card" onClick={() => window.open("http://localhost:8082/swagger-ui/", "_blank")}>
          <FontAwesomeIcon icon={faMoneyBill} size="2x"/>
          <h2>Rental Service</h2>
          <p>Access the Rental Service Swagger</p>
        </div>
        <div className="card" onClick={() => window.open("http://localhost:8083/swagger-ui/", "_blank")}>
          <FontAwesomeIcon icon={faLock} size="2x"/>
          <h2>Reservation Service</h2>
          <p>Access the Reservation Service Swagger</p>
        </div>
      </div>
    </div>
  )
}

export default App
