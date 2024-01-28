USE localoge;

CREATE TABLE IF NOT EXISTS housings (
    id INT AUTO_INCREMENT,
    `type` VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    ownerId INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS rentals (
    id INT AUTO_INCREMENT,
    property_id INT NOT NULL,
    durationInDays INT NOT NULL,
    dailyPrice DOUBLE NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS reservations (
    id INT AUTO_INCREMENT,
    rentalId INT NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);