import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import EventListPage from './pages/EventListPage';
import EventDetailPage from './pages/EventDetailPage';
import EventCreationPage from './pages/EventCreationPage';
import EventUpdatePage from './pages/EventUpdatePage';
import { EventProvider } from './context/EventContext';

function App() {
  return (
    <EventProvider>
      <Router>
        <Routes>
          <Route path="/" element={<EventListPage />} />
          <Route path="/event/:id" element={<EventDetailPage />} />
          <Route path="/create" element={<EventCreationPage />} />
          <Route path="/events/:id/update" element={<EventUpdatePage />} />
        </Routes>
      </Router>
    </EventProvider>
  );
}

export default App;