public class PostFactory {
    public Post getPost(String postType){
        if(postType == null){
            return null;
        }
        if(postType.equalsIgnoreCase("Letter")){
            return new Letter();
        }
        else if(postType.equalsIgnoreCase("Package")) {
            return new Package();
        }
        else if(postType.equalsIgnoreCase("Parcel")) {
            return new Parcel();
        }
        return null;
    }
}
