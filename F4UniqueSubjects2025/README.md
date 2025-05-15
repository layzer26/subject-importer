# F4 Subject Choices Importer

A Java application to import and manage Form 4 subject choices data into a MySQL database.

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/form4/
│   │       ├── service/    # Business logic
│   │       ├── util/       # Utility classes
│   │       └── App.java    # Main application
│   └── resources/
│       └── data/          # CSV data files
```

## Setup

1. Ensure you have:
   - Java 17 or higher
   - MySQL 8.0 or higher
   - Maven

2. Database Configuration:
   - Host: localhost
   - Port: 3306
   - Database: f4subjectchoices
   - Username: root

3. Build and Run:
```bash
mvn clean compile exec:java
```

## Future Improvements

### Code Structure
- [ ] Implement proper layering (Repository pattern)
- [ ] Add domain models (Student, Subject, SubjectChoice)
- [ ] Add configuration management
- [ ] Implement proper exception handling
- [ ] Add logging framework
- [ ] Add unit tests

### Data Management
- [ ] Validate subject names against approved list
- [ ] Create separate subjects table for normalization
- [ ] Implement proper subject code mapping
- [ ] Add duplicate student entry prevention
- [ ] Improve student ID generation logic

### Database
- [ ] Add database migration scripts
- [ ] Implement connection pooling
- [ ] Add indexes for performance
- [ ] Add foreign key constraints

## Contributing

1. Create a feature branch
2. Make your changes
3. Submit a pull request

## License

This project is proprietary and confidential