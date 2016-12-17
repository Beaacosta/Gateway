package es.uc3m.tiw.dominios;

public class Conversacion {

		private Usuario emisor;
		private Usuario receptor;
		
		
		public Conversacion() {
			super();
		}

		public Conversacion(Usuario emisor, Usuario receptor) {
			super();
			this.emisor = emisor;
			this.receptor = receptor;
		}
		
		public Usuario getEmisor() {
			return emisor;
		}
		public void setEmisor(Usuario emisor) {
			this.emisor = emisor;
		}
		public Usuario getReceptor() {
			return receptor;
		}
		public void setReceptor(Usuario receptor) {
			this.receptor = receptor;
		}
		
		
}
