export default function(app) {
  app.get('/', (_,res) => res.send('telebum server api'))

  app.route('/*')
    .get((req, res) => {
      res.status(404).json({ error: 'Invalid route' })
    })
}
