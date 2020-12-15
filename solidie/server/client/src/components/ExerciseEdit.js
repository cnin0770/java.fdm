import React, { Component } from 'react';
import { instanceOf } from 'prop-types';
import { Link, withRouter } from 'react-router-dom';
import { withCookies, Cookies } from 'react-cookie';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

import AppNavbar from '../AppNavbar';

class ExerciseEdit extends Component {
  static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  };

  emptyItem = {
    title: '',
    repetition: '',
    category: '',
    comment: '',
    measure: '',
    measureUnit: '',
    count: '',
    countUnit: ''
  };

  constructor(props) {
    super(props);
    const {cookies} = props;
    this.state = {
      item: this.emptyItem,
      csrfToken: cookies.get('XSRF-TOKEN')
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      try {
        const exercise = await (await fetch(`/api/exercises/${this.props.match.params.id}`, { credentials: 'include' })).json();
        this.setState({ item: exercise });
      } catch (error) {
        this.props.history.push('/');
      }
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item, csrfToken} = this.state;

    await fetch('/api/exercises' + (item.id ? '/' + item.id : ''), {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'X-XSRF-TOKEN': csrfToken,
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
      credentials: 'include'
    });
    this.props.history.push('/exercises');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit Exercise' : 'Add Exercise'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="title">Title</Label>
            <Input type="text" name="title" id="title" value={item.title || ''}
                   onChange={this.handleChange} autoComplete="title"/>
          </FormGroup>
          <FormGroup>
            <Label for="repetition">Sets</Label>
            <Input type="number" name="repetition" id="repetition" min="1" max="8" value={item.repetition || ''}
                   onChange={this.handleChange} autoComplete="repetition"/>
          </FormGroup>
          <FormGroup>
            <Label for="category">Category</Label>
            <Input type="text" name="category" id="category" value={item.category || ''}
                   onChange={this.handleChange} autoComplete="category"/>
          </FormGroup>
          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="measure">Measure</Label>
              <Input type="number" name="measure" id="measure" value={item.measure || ''}
                     onChange={this.handleChange} autoComplete="measure"/>
            </FormGroup>
            <FormGroup className="col-md-3 mb-3">
              <Label for="measureUnit">Measure Unit</Label>
              <Input type="text" name="measureUnit" id="measureUnit" value={item.measureUnit || ''}
                     onChange={this.handleChange} autoComplete="measureUnit"/>
            </FormGroup>
          </div>
          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="count">Count</Label>
              <Input type="number" name="count" id="count" value={item.count || ''}
                     onChange={this.handleChange} autoComplete="count"/>
            </FormGroup>
            <FormGroup className="col-md-3 mb-3">
              <Label for="countUnit">Count Unit</Label>
              <Input type="text" name="countUnit" id="countUnit" value={item.countUnit || ''}
                     onChange={this.handleChange} autoComplete="countUnit"/>
            </FormGroup>
          </div>
          <FormGroup>
            <Label for="comment">Comment</Label>
            <Input type="text" name="comment" id="comment" value={item.comment || ''}
                   onChange={this.handleChange} autoComplete="comment"/>
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/exercises">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withCookies(withRouter(ExerciseEdit));