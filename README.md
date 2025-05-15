# St Alban Data Projects

## Overview
Project to analyze and process Form 4 (Grade 11) subject choices data.

## Project Structure
```
F4UniqueSubjects2025/
├── src/
│   ├── main/
│   │   ├── java/                    # Java source files
│   │   │   └── com/
│   │   │       └── form4/
│   │   └── resources/
│   │       └── data/                # Data files (not tracked in git)
│   └── test/
│       └── java/                    # Test files
├── .gitignore                       # Git ignore rules
├── pom.xml                          # Maven configuration
└── README.md                        # Project documentation
```

## Data Format
### CSV Structure
| Column Name     | Description                |
|----------------|----------------------------|
| Pupil          | Student's full name        |
| Total Subjects | Count of subjects chosen   |
| 1-10           | Individual subject codes   |

### Subject Codes
| Subject                    | Code |
|---------------------------|------|
| Mathematics               | MAT  |
| English                   | ENG  |
| Physical Science          | PHY  |
| Life Science              | LIF  |
| Information Technology    | INF  |
| Computer Applied Tech     | CAT  |
| Music                     | MUS  |
| Drama                     | DRA  |
| Geography                 | GEO  |
| History                   | HIS  |
| Engineering Graphics      | EGD  |
| French                    | FRE  |
| Accounting                | ACC  |
| Business Studies          | BUS  |

## Setup
1. Clone the repository
2. Place your CSV data file in `src/main/resources/data/`
3. Run `mvn clean install`

## Usage
Run the main application:
```bash
mvn exec:java -Dexec.mainClass="com.form4.App"
```

## Contributing
1. Create a feature branch
2. Make your changes
3. Submit a pull request

## License
MIT License

Copyright (c) 2025 Liam Naidoo