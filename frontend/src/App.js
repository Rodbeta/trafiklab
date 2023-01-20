import logo from "./logo.svg";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { Component } from "react";
import Accordion from "react-bootstrap/Accordion";

class App extends Component {
  state = {
    lines: [],
  };

  constructor(props) {
    super(props);
    this.state = { lines: [], isLoaded: false };
  }

// async
  componentDidMount() {
    console.log("componentDidMount");
    // await
    fetch("/lines")
      .then((response) => response.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            lines: result,
          });
        },
        (error) => {
          this.setState({
            isLoaded: true,
            error,
          });
        }
      );
  }

  shouldComponentUpdate() {
    return !this.state.isLoaded;
  }

  render() {
    const {lines} = this.state;

    return (
      <div>
        <h2>10 SL lines</h2>
        <Accordion flush>
          {Object.entries(this.state.lines).map(([lineNr, stopNames]) => {
            return (
              <Accordion.Item key={lineNr} eventKey={lineNr}>
                <Accordion.Header>{lineNr}</Accordion.Header>
                <Accordion.Body>
                  <ul>
                    {stopNames.map((name) => (
                      <li key={name.toString()}>{name}</li>
                    ))}
                  </ul>
                </Accordion.Body>
              </Accordion.Item>
            );
          })}
        </Accordion>
      </div>
    );
  }
}
export default App;
